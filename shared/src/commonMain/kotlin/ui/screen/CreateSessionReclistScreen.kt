package ui.screen

import LocalAppActionStore
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.LocalFileInteractor
import kotlinx.coroutines.flow.collectLatest
import model.Action
import model.Actions
import repository.LocalReclistRepository
import ui.common.ActionMenu
import ui.common.FloatingActionButtonWrapper
import ui.common.LocalToastController
import ui.common.ScrollableLazyColumn
import ui.model.Screen
import ui.string.*

object CreateSessionReclistScreen : Screen {
    @Composable
    override fun getTitle(): String = string(Strings.CreateSessionReclistScreenTitle)

    @Composable
    override fun Content() = ScreenContent()

    @Composable
    override fun Actions() = ScreenActions()
}

@Composable
private fun CreateSessionReclistScreen.ScreenActions() {
    val model = rememberCreateSessionReclistScreenModel()
    model.ActionButtonWrapper {
        ActionMenu { closeMenu ->
            val fileInteractor = LocalFileInteractor.current
            val repository = LocalReclistRepository.current
            val toastController = LocalToastController.current
            DropdownMenuItem(
                onClick = {
                    closeMenu()
                    Actions.importReclist(fileInteractor, repository, toastController)
                },
            ) {
                Text(text = string(Strings.CommonImport))
            }
            DropdownMenuItem(
                onClick = {
                    closeMenu()
                    model.startSelectingForDeletion()
                },
            ) {
                Text(text = string(Strings.CommonEdit))
            }
        }
    }
}

@Composable
private fun CreateSessionReclistScreen.ScreenContent() {
    val model = rememberCreateSessionReclistScreenModel()
    val reclists by model.reclists.collectAsState()
    val reclistRepository = LocalReclistRepository.current
    val navigator = LocalNavigator.currentOrThrow
    val appActionStore = LocalAppActionStore.current
    val fileInteractor = LocalFileInteractor.current
    val toastController = LocalToastController.current

    LaunchedEffect(appActionStore) {
        appActionStore.actions.collectLatest {
            when (it) {
                Action.ImportReclist -> Actions.importReclist(fileInteractor, reclistRepository, toastController)
                Action.EditList -> model.startSelectingForDeletion()
                Action.Exit -> navigator.pop()
                else -> Unit
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)) {
        Column(modifier = Modifier.fillMaxSize()) {
            val titleText = model.getWrappedTitleText(string(Strings.CreateSessionReclistScreenAllReclists))
            Text(
                text = titleText,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 24.dp),
                style = MaterialTheme.typography.h5,
            )
            ItemDivider()
            Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                ScrollableLazyColumn {
                    items(reclists, key = { it }) {
                        model.ItemRow(it, model::select) {
                            Text(it)
                        }
                        ItemDivider()
                    }
                }
                if (reclists.isEmpty()) {
                    Text(
                        text = string(Strings.CreateSessionReclistScreenEmpty),
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
        FloatingActionButtonWrapper(model)
    }
}

@Composable
private fun ItemDivider() {
    Divider(modifier = Modifier.padding(start = 16.dp))
}
