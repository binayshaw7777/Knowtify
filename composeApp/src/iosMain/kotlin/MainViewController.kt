import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getDictionaryDatabase
import util.ApplicationComponent

fun MainViewController() = ComposeUIViewController {
    val database = remember {
        getDictionaryDatabase()
    }
    App(database)
}

fun initialize() {
    ApplicationComponent.init()
}