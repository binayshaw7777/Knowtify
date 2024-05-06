import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import data.database.DictionaryDao
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(dictionaryDao: DictionaryDao) {
    MaterialTheme {
        MainContent(dictionaryDao)
    }
}

@Composable
fun MainContent(dictionaryDao: DictionaryDao) {
    Column {
        Navigation(dictionaryDao)
    }
}