import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.room.RoomDatabase
import data.database.DictionaryDatabase
import di.appModule
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import ui.KnowtifyTheme

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<DictionaryDatabase>) {
    KoinApplication(application = {
        modules(appModule(databaseBuilder))
    }) {
        KnowtifyTheme {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    Box(modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)) {
        Navigation()
    }
}