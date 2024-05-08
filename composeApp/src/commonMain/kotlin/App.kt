import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.room.RoomDatabase
import data.database.DictionaryDatabase
import di.appModule
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<DictionaryDatabase>) {
    KoinApplication(application = {
        modules(appModule(databaseBuilder))
    }) {
        MaterialTheme {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    Column {
        Navigation()
    }
}