import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.room.RoomDatabase
import data.database.DictionaryDatabase
import di.appModule
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import ui.KnowtifyTheme
import util.ApplicationComponent
import util.ApplicationComponent.coreComponent

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<DictionaryDatabase>) {

    var isDarkModeEnabled by remember { mutableStateOf(false) }

    KoinApplication(application = {
        modules(appModule(databaseBuilder))
    }) {
        LaunchedEffect(true) {
            isDarkModeEnabled = coreComponent.appPreferences.isDarkModeEnabled()
        }
        KnowtifyTheme(
            darkTheme = isDarkModeEnabled
        ) {
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