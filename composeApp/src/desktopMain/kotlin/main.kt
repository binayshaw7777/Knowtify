import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import database.getDatabaseBuilder
import util.ApplicationComponent

fun main() = application {

    ApplicationComponent.init()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Knowtify",
    ) {
        val database = remember { getDatabaseBuilder() }
        App(database)
    }
}