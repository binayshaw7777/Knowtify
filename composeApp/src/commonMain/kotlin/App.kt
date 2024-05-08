import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.room.RoomDatabase
import data.database.DictionaryDao
import data.database.DictionaryDatabase
import data.database.getRoomDatabase
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<DictionaryDatabase>) {

    MaterialTheme {

        val database = remember { getRoomDatabase(databaseBuilder) }
        val dictionaryDao = remember { database.dictionaryDao() }

        MainContent(dictionaryDao)
    }
}

@Composable
fun MainContent(dictionaryDao: DictionaryDao) {
    Column {
        Navigation(dictionaryDao)
    }
}