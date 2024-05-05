import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
//    initializeKoin()

    MaterialTheme {
        MainContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    Column {
        Navigation()
    }
}