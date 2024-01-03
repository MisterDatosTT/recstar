package model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.input.key.Key
import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import kotlinx.serialization.Serializable
import ui.string.*
import util.isDesktop

private typealias StringLanguage = Language

@Immutable
@Serializable
data class AppPreference(
    val language: Language = Language.Auto,
    val theme: Theme = if (isDesktop) Theme.Dark else Theme.System,
    val orientation: ScreenOrientation = if (isDesktop) ScreenOrientation.Landscape else ScreenOrientation.Auto,
    val customContentRootPath: String? = null,
    val recording: Recording = Recording(),
) : JavaSerializable {
    enum class Language(private val language: StringLanguage?) : LocalizedText {
        Auto(null),
        English(StringLanguage.English),
        SimplifiedChinese(StringLanguage.ChineseSimplified),
        Japanese(StringLanguage.Japanese),
        ;

        fun getLanguage(): StringLanguage = language ?: findBestMatchedLanguage()

        override val textKey: Strings get() = error("Not accessible")

        @Composable
        override fun getText(): String = language?.displayName ?: string(Strings.PreferenceLanguageAuto)
    }

    enum class Theme(override val textKey: Strings) : LocalizedText {
        System(Strings.PreferenceThemeSystem),
        Light(Strings.PreferenceThemeLight),
        Dark(Strings.PreferenceThemeDark),
    }

    enum class ScreenOrientation(override val textKey: Strings) : LocalizedText {
        Auto(Strings.PreferenceOrientationAuto),
        Portrait(Strings.PreferenceOrientationPortrait),
        Landscape(Strings.PreferenceOrientationLandscape),
    }

    /**
     * Recording settings.
     *
     * @param continuous Whether to continue to record the next sentence based on the guide audio config.
     * @param trim Whether to trim the recorded audio based on the guide audio config.
     * @param autoListenBack Whether to automatically listen back to the recorded audio after stopping recording
     *     manually.
     * @param autoNext Whether to automatically switch to the next sentence after stopping recording manually.
     * @param recordWhileHolding Whether to record while holding the record button.
     * @param recordingShortKey The short key to start/stop recording.
     */
    @Immutable
    @Serializable
    data class Recording(
        val continuous: Boolean = false,
        val trim: Boolean = true,
        val autoListenBack: Boolean = false,
        val autoNext: Boolean = false,
        val recordWhileHolding: Boolean = false,
        val recordingShortKey: RecordingShortKey = RecordingShortKey.Enter,
    ) : JavaSerializable

    enum class RecordingShortKey(override val textKey: Strings) : LocalizedText {
        Enter(Strings.PreferenceRecordingShortKeyEnter),
        R(Strings.PreferenceRecordingShortKeyR),
        ;

        fun getKey(): Key =
            when (this) {
                Enter -> Key.Enter
                R -> Key.R
            }
    }
}
