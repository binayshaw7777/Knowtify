package presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import co.touchlab.kermit.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import navigation.LocalNavHost
import org.koin.compose.koinInject

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
                title = { Text(text = "Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        scope.launch(Dispatchers.IO) {
                            Logger.d("Share: ${uiState?.word}")
                        }
                    }) {
                        Icon(Icons.Filled.Share, contentDescription = "Refresh")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFEEF1F6))
                .padding(16.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.Start
        ) {

            LaunchedEffect(Unit) {
                Logger.d("Word Meaning: $uiState")
            }


            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = uiState?.word ?: "",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                uiState?.phonetic?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily.Serif
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier.padding(vertical = 18.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Part of speech",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

//            LazyColumn {
//
//                val noun = uiState?.meanings?.find { it.partOfSpeech == "noun" }
//                noun?.let {
//                    item {
//                        DefinitionItem(
//                            noun.partOfSpeech,
//                            noun.definitions.find { it.definition.isNotEmpty() }?.definition ?: "",
//                        )
//                    }
//                }
//
//                val verb = uiState?.meanings?.find { it.partOfSpeech == "verb" }
//                verb?.let {
//                    item {
//                        DefinitionItem(
//                            verb.partOfSpeech,
//                            verb.definitions.find { it.definition.isNotEmpty() }?.definition ?: "",
//                            TextStyle(
//                                color = Color.Black,
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Medium
//                            )
//                        )
//                    }
//                }
//            }
        }
    }
}