import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getDictionaryDatabase

fun MainViewController() = ComposeUIViewController {
    val database = remember {
        getDictionaryDatabase()
    }
    App(database)
}