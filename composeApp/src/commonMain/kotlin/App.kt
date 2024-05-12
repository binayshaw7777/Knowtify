import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.room.RoomDatabase
import data.database.DictionaryDatabase
import di.appModule
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import presentation.component.Theme
import ui.KnowtifyTheme

@Composable
@Preview
fun App(
    databaseBuilder: RoomDatabase.Builder<DictionaryDatabase>,
) {


    KoinApplication(application = {
        modules(appModule(databaseBuilder))
    }) {
        val themeViewModel: ThemeViewModel = koinInject()
        val appTheme by themeViewModel.currentAppTheme.collectAsState(isSystemInDarkTheme())

        KnowtifyTheme(
            darkTheme = when (appTheme) {
                Theme.Dark -> true
                Theme.Light -> false
                else -> {
                    isSystemInDarkTheme()
                }
            }
        ) {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Navigation()
    }
}