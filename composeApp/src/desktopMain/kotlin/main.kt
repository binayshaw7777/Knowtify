import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import database.getDatabaseBuilder

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Knowtify",
    ) {
        val database = remember { getDatabaseBuilder() }
        App(database)
    }
}