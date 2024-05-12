package presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.MeaningDTO
import data.model.WordItemDTO
import knowtify.composeapp.generated.resources.Res
import knowtify.composeapp.generated.resources.back
import knowtify.composeapp.generated.resources.bullet
import knowtify.composeapp.generated.resources.detail
import knowtify.composeapp.generated.resources.part_of_speech
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import navigation.LocalNavHost
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import util.titleCaseFirstChar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    meaningId: Int,
    detailViewModel: DetailViewModel = koinInject()
) {

    val navController = LocalNavHost.current
    val scope = rememberCoroutineScope()

    val uiState by detailViewModel.wordMeaning.collectAsState()

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            detailViewModel.getWordMeaningFromId(meaningId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.detail)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(Res.string.back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.Start
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = uiState?.word ?: "",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                if (uiState?.phonetic?.isNotEmpty() == true) {
                    Text(
                        text = uiState!!.phonetic!!,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily.Serif
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier.padding(vertical = 18.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = stringResource(Res.string.part_of_speech),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black
                    )
                )
            }
            uiState?.let {
                WordResult(wordItem = it)
            }
        }
    }
}

@Composable
fun WordResult(wordItem: WordItemDTO) {
    LazyColumn {

        wordItem.meanings?.let { meanings ->
            items(meanings.size) { index ->
                Meaning(
                    meaning = meanings[index],
                    index = index
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun Meaning(
    meaning: MeaningDTO,
    index: Int
) {

    Column {

        Text(
            text = meaning.partOfSpeech?.titleCaseFirstChar() ?: index.toString(),
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )

        if (meaning.definitions?.definition?.isNotEmpty() == true) {

            Spacer(modifier = Modifier.height(8.dp))

            Row {

                Text(
                    text = stringResource(Res.string.bullet),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = meaning.definitions.definition,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }
        }

        if (meaning.definitions?.example?.isNotEmpty() == true) {

            Spacer(modifier = Modifier.height(8.dp))

            Row {

                Text(
                    text = stringResource(Res.string.bullet),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = meaning.definitions.example,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}