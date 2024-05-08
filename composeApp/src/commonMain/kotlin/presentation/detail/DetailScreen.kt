package presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.touchlab.kermit.Logger
import data.database.DictionaryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import navigation.LocalNavHost
import repository.DetailRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    meaningId: Int,
    dictionaryDao: DictionaryDao
) {

    val navController = LocalNavHost.current
    val scope = rememberCoroutineScope()

    val repository by remember { mutableStateOf(DetailRepository()) }
    val detailViewModel: DetailViewModel = viewModel { DetailViewModel(repository, dictionaryDao) }

    val uiState by detailViewModel.wordMeaning.collectAsState()

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            detailViewModel.getWordMeaningFromId(meaningId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp).padding(it)
        ) {

            LaunchedEffect(Unit) {
                Logger.d("Word Meaning: $uiState")
            }

            Text(text = "Detail Screen: ${uiState?.word}")
        }
    }
}