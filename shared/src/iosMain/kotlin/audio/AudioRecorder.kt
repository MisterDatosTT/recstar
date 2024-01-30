package audio

import const.WavFormat
import io.File
import io.toFile
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.io.Buffer
import kotlinx.io.asSink
import kotlinx.io.buffered
import kotlinx.io.readByteArray
import kotlinx.io.readIntLe
import kotlinx.io.readString
import kotlinx.io.writeIntLe
import kotlinx.io.writeString
import platform.AVFAudio.AVAudioEngine
import platform.AVFAudio.AVAudioPCMBuffer
import platform.AVFAudio.AVAudioRecorder
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVFormatIDKey
import platform.AVFAudio.AVLinearPCMBitDepthKey
import platform.AVFAudio.AVLinearPCMIsBigEndianKey
import platform.AVFAudio.AVLinearPCMIsFloatKey
import platform.AVFAudio.AVNumberOfChannelsKey
import platform.AVFAudio.AVSampleRateKey
import platform.AVFAudio.setActive
import platform.CoreAudioTypes.kAudioFormatLinearPCM
import platform.Foundation.NSOutputStream
import repository.AppPreferenceRepository
import ui.common.UnexpectedErrorNotifier
import ui.model.AppContext
import util.Log
import util.runCatchingCancellable
import util.withNSError
import util.withNSErrorCatching

@OptIn(ExperimentalForeignApi::class)
class AudioRecorderImpl(
    private val listener: AudioRecorder.Listener,
    context: AppContext,
    private val unexpectedErrorNotifier: UnexpectedErrorNotifier,
    private val appPreferenceRepository: AppPreferenceRepository,
) : AudioRecorder {
    private var recorder: AVAudioRecorder? = null
    private var engine: AVAudioEngine? = null
    private var job: Job? = null
    private var cleanupJob: Job? = null
    private val scope = context.coroutineScope

    override fun start(output: File) {
        if (job?.isActive == true) {
            Log.w("AudioRecorderImpl.start: already started")
            return
        }
        waveData.clear()
        _waveDataFlow.value = arrayOf(FloatArray(0))
        job = scope.launch(Dispatchers.IO) {
            runCatchingCancellable {
                withNSError { e ->
                    val settings = mapOf<Any?, Any>(
                        AVFormatIDKey to kAudioFormatLinearPCM,
                        AVSampleRateKey to 44100.0,
                        AVNumberOfChannelsKey to 1,
                        AVLinearPCMBitDepthKey to 16,
                        AVLinearPCMIsBigEndianKey to false,
                        AVLinearPCMIsFloatKey to false,
                    )
                    val url = output.toNSURL()
                    Log.i("AudioRecorderImpl.start: url: $url")
                    recorder = AVAudioRecorder(url, settings, e)

                    val engine = AVAudioEngine()
                    this@AudioRecorderImpl.engine = engine
                    val input = engine.inputNode
                    val bus = 0L.toULong()
                    val format = input.inputFormatForBus(bus)
                    input.installTapOnBus(bus, 2048.toUInt(), format) { buffer, _ ->
                        if (buffer != null) {
                            addWaveData(buffer)
                        }
                    }
                    withNSError { e0 ->
                        engine.startAndReturnError(e0)
                    }
                    recorder?.record()
                    withContext(Dispatchers.Main) {
                        listener.onStarted()
                    }
                }
            }.onFailure {
                unexpectedErrorNotifier.notify(it)
                dispose()
            }
        }
    }

    override fun stop() {
        Log.d("AudioRecorderImpl.stop")
        cleanupJob = scope.launch(Dispatchers.IO) {
            runCatchingCancellable {
                val recordedUrl = recorder?.url
                Log.d("AudioRecorderImpl.stop: recordedUrl: $recordedUrl")
                recorder?.stop()
                recorder = null
                job?.cancelAndJoin()
                engine?.stop()
                engine = null
                recordedUrl?.let { rewriteHeader(it.toFile()) }
                Log.i("AudioRecorderImpl.stop: stopped")
                withContext(Dispatchers.Main) {
                    listener.onStopped()
                }
            }.onFailure {
                unexpectedErrorNotifier.notify(it)
                dispose()
            }
        }
    }

    override fun isRecording(): Boolean = recorder?.recording == true

    override fun dispose() {
        runCatching {
            cleanupJob?.cancel()
            job?.cancel()
            recorder?.takeIf { it.recording }?.stop()
            recorder = null
            engine?.takeIf { it.running }?.stop()
            engine = null
            withNSErrorCatching { e ->
                AVAudioSession.sharedInstance().setActive(false, e)
            }.onFailure {
                Log.e("Failed to free AVAudioSession", it)
            }
        }.onFailure {
            unexpectedErrorNotifier.notify(it)
        }
    }

    private fun rewriteHeader(file: File) {
        val source = file.source()
        val chunkId = source.readByteArray(4)
        source.readIntLe() // chunk size (unused)
        val format = source.readString(4)
        val chunkContent = Buffer()
        while (source.exhausted().not()) {
            val subchunkId = source.readString(4)
            val subchunkSize = source.readIntLe()
            if (subchunkId !in listOf(WavFormat.SUBCHUNK_1_ID, WavFormat.SUBCHUNK_2_ID)) {
                source.skip(subchunkSize.toLong())
                continue
            }
            chunkContent.writeString(subchunkId)
            chunkContent.writeIntLe(subchunkSize)
            chunkContent.write(source.readByteArray(subchunkSize))
        }
        source.close()
        file.delete()
        val dest = NSOutputStream(file.toNSURL(), true).asSink().buffered()
        dest.write(chunkId)
        dest.writeIntLe(chunkContent.size.toInt() + 4)
        dest.writeString(format)
        dest.write(chunkContent, chunkContent.size)
        dest.close()
    }

    private fun addWaveData(buffer: AVAudioPCMBuffer) {
        val frameLength = buffer.frameLength.toInt()
        val channelData = buffer.floatChannelData?.get(0) ?: return
        for (i in 0 until frameLength) {
            waveData.add(channelData[i])
        }
        _waveDataFlow.value = waveData.map { arrayOf(it).toFloatArray() }.toTypedArray()
    }

    private val waveData = mutableListOf<Float>()
    private val _waveDataFlow = MutableStateFlow(arrayOf(FloatArray(0)))
    override val waveDataFlow: Flow<WavData> = _waveDataFlow
}

actual class AudioRecorderProvider actual constructor(
    private val listener: AudioRecorder.Listener,
    private val context: AppContext,
    private val unexpectedErrorNotifier: UnexpectedErrorNotifier,
    private val appPreferenceRepository: AppPreferenceRepository,
) {
    actual fun get(): AudioRecorder =
        AudioRecorderImpl(
            listener = listener,
            context = context,
            unexpectedErrorNotifier = unexpectedErrorNotifier,
            appPreferenceRepository = appPreferenceRepository,
        )
}
