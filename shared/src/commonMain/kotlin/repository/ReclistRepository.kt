package repository

import androidx.compose.runtime.staticCompositionLocalOf
import io.File
import io.Paths
import io.reclistsDirectory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import model.Reclist
import model.parseReclist
import util.Log

/**
 * A repository to manage reclist files.
 */
class ReclistRepository {
    private val folder = Paths.reclistsDirectory.also {
        if (!it.exists()) {
            it.mkdirs()
        }
    }

    private val map = mutableMapOf<String, Reclist>()

    /**
     * Imports a reclist from the given file.
     *
     * @return true if the import was successful, false otherwise.
     */
    fun import(file: File): Boolean {
        Log.i("ReclistRepository.import: ${file.absolutePath}")
        val reclist = parseReclist(file)
            .onFailure {
                Log.e("ReclistRepository.import: failed to parse ${file.absolutePath}", it)
            }
            .getOrNull() ?: return false
        val name = file.nameWithoutExtension
        if (map.containsKey(name)) {
            Log.w("ReclistRepository.import: $name already exists, overwriting...")
        }
        map[name] = reclist
        file.copyTo(folder.resolve(file.name), overwrite = true)
        _items.value = listOf(name) + _items.value.minus(name)
        return true
    }

    private val _items = MutableStateFlow(emptyList<String>())

    /**
     * The list of reclist names.
     */
    val items: StateFlow<List<String>> = _items

    /**
     * Fetches the list of reclists.
     */
    fun fetch() {
        val items = folder.listFiles()
            .filter { it.extension == "txt" }
            .map { it.nameWithoutExtension }
        _items.value = items
    }

    /**
     * Gets the reclist with the given name.
     */
    fun get(name: String): Reclist = map[name] ?: parseReclist(folder.resolve("$name.txt")).getOrThrow()

    /**
     * Deletes the reclists with the given names.
     */
    fun delete(names: List<String>) {
        names.forEach { name ->
            val file = folder.resolve(name)
            file.delete()
        }
        _items.value = _items.value.filterNot { it in names }
    }
}

val LocalReclistRepository = staticCompositionLocalOf<ReclistRepository> { error("No ReclistRepository provided") }
