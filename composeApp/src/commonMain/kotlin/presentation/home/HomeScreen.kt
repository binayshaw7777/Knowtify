package presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.touchlab.kermit.Logger
import navigation.LocalNavHost
import navigation.Screens
import presentation.component.SearchedItem
import repository.HomeRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val navController = LocalNavHost.current
    var searchBarQuery by rememberSaveable { mutableStateOf("") }
    var isSearchActive by rememberSaveable { mutableStateOf(false) }

    val repository by remember { mutableStateOf(HomeRepository()) }
    val homeViewModel: HomeViewModel = viewModel { HomeViewModel(repository) }
    val uiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        Logger.d("Dictionary Response: $uiState")
        uiState?.let {
            Logger.d("Dictionary Response is not null and hence navigating: $it")
            val number = (0..10).random()
            navController.navigate("${Screens.Detail.route}/$number")
            homeViewModel.setUiState(null)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                actions = {
                    IconButton(onClick = { navController.navigate("setting") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Goto Settings Screen")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFEEF1F6))
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
                repeat(10) {
                    item {
                        SearchedItem(
                            it,
                            "Foodie",
                            "A person with a special interest in or knowledge of..."
                        ) {
                            Logger.d("Clicked on item $it")
                        }
                    }
                }
            }


            SearchBar(
                modifier = Modifier.weight(1f).wrapContentSize(),
                query = searchBarQuery,
                onQueryChange = { searchBarQuery = it },
                active = isSearchActive,
                onActiveChange = { isSearchActive = false },
                colors = SearchBarDefaults.colors(Color.White),
                onSearch = {
                    Logger.d("Clicked on search")
                    if (searchBarQuery.isNotEmpty()) {
                        homeViewModel.getDictionary(searchBarQuery.trim())
                        searchBarQuery = ""
                    }
                },
                placeholder = { Text("Search any word") },
                trailingIcon = {
                    if (searchBarQuery.isEmpty()) {
                        IconButton(onClick = {
                            // Speech to text
                            Logger.d("Recording audio")
                        }) {
                            Icon(Icons.Default.Mic, contentDescription = "Mic")
                        }
                    } else {
                        IconButton(onClick = {
                            homeViewModel.getDictionary(searchBarQuery)
                            searchBarQuery = ""
                        }) {
                            Icon(Icons.Default.Send, contentDescription = "Send")
                        }
                    }
                }
            ) {}
        }
    }
}