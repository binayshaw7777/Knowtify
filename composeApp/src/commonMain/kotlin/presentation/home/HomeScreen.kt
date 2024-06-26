package presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import knowtify.composeapp.generated.resources.Res
import knowtify.composeapp.generated.resources.goto_setting_screen
import knowtify.composeapp.generated.resources.home
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import navigation.LocalNavHost
import navigation.Screens
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import presentation.component.LoadingProgressDialog
import presentation.component.SearchBar
import presentation.component.SearchedItem
import presentation.home.states.HomeScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinInject()
) {

    val navController = LocalNavHost.current
    val scope = rememberCoroutineScope()

    var searchBarQuery by rememberSaveable { mutableStateOf("") }

    val dictionaryDatabase by homeViewModel.dictionaryDatabase.collectAsState()

    val insertedDictionary by homeViewModel.insertedDictionary.collectAsState()

    val homeScreenState by homeViewModel.homeViewState.collectAsState()

    when (homeScreenState) {
        is HomeScreenState.Clear -> {}
        is HomeScreenState.Loading -> {
            LoadingProgressDialog()
        }

        is HomeScreenState.Error -> {
        }

        is HomeScreenState.Success -> {
        }
    }

    LaunchedEffect(insertedDictionary) {
        insertedDictionary?.let {
            navController.navigate("${Screens.Detail.route}/${it.id}")
            homeViewModel.clearStates()
        }
    }

    LaunchedEffect(Unit) {
        homeViewModel.getAllDictionarySearch()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.home)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                actions = {
                    IconButton(onClick = { navController.navigate(Screens.Setting.route) }) {
                        Icon(Icons.Default.Settings, contentDescription = stringResource(Res.string.goto_setting_screen))
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            LazyColumn(
                modifier = Modifier.weight(9f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(dictionaryDatabase) { item ->
                    SearchedItem(
                        item
                    ) {
                        navController.navigate("${Screens.Detail.route}/$it")
                    }
                }
            }

            SearchBar(
                search = searchBarQuery,
                onValueChange = { searchBarQuery = it }
            ) {
                scope.launch(Dispatchers.IO) {
                    homeViewModel.getDictionary(searchBarQuery.trim())
                    searchBarQuery = ""
                }
            }
        }
    }
}