package presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import knowtify.composeapp.generated.resources.Res
import knowtify.composeapp.generated.resources.cancel
import knowtify.composeapp.generated.resources.delete
import knowtify.composeapp.generated.resources.delete_history
import knowtify.composeapp.generated.resources.delete_history_description
import knowtify.composeapp.generated.resources.general
import knowtify.composeapp.generated.resources.go_back
import knowtify.composeapp.generated.resources.invite_others
import knowtify.composeapp.generated.resources.setting
import knowtify.composeapp.generated.resources.theme
import navigation.LocalNavHost
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import presentation.component.SettingItem
import presentation.component.Theme
import presentation.component.ThemeSelectionDialog
import util.Constant.APP_REPOSITORY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting(
    settingViewModel: SettingViewModel = koinInject()
) {

    val navController = LocalNavHost.current
    val uriHandler = LocalUriHandler.current

    var showThemeSelectionDialog by remember { mutableStateOf(false) }
    val isDarkModeEnabled by settingViewModel.isDarkModeEnabled.collectAsState()
    var showDeleteHistoryDialog by remember { mutableStateOf(false) }

    when {
        showDeleteHistoryDialog -> {

            AlertDialog(
                onDismissRequest = { showDeleteHistoryDialog = false },
                title = { Text(stringResource(Res.string.delete_history)) },
                text = { Text(stringResource(Res.string.delete_history_description)) },
                icon = {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = stringResource(Res.string.delete_history)
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            settingViewModel.deleteHistory()
                            showDeleteHistoryDialog = false
                            navController.popBackStack()
                        }
                    ) {
                        Text(stringResource(Res.string.delete))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteHistoryDialog = false }) {
                        Text(stringResource(Res.string.cancel))
                    }
                }
            )
        }

        showThemeSelectionDialog -> {
            ThemeSelectionDialog(
                onThemeChange = { theme ->
                    when (theme) {
                        Theme.Light -> settingViewModel.changeDarkMode(false)
                        Theme.Dark -> settingViewModel.changeDarkMode(true)
                    }
                    showThemeSelectionDialog = false
                },
                onDismissRequest = { showThemeSelectionDialog = false },
                currentTheme = if (isDarkModeEnabled) Theme.Dark else Theme.Light
            )
        }
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.setting)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {

            LazyColumn {
                item {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(Res.string.general),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
                item {
                    SettingItem(
                        onClick = {
                            showThemeSelectionDialog = true
                        },
                        imageVector = if (isSystemInDarkTheme()) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
                        itemName = stringResource(Res.string.theme)
                    )
                }
                item {
                    SettingItem(
                        onClick = {
                            uriHandler.openUri(APP_REPOSITORY)
                        },
                        imageVector = Icons.Outlined.PeopleAlt,
                        itemName = stringResource(Res.string.invite_others)
                    )
                }
                item {
                    SettingItem(
                        onClick = {
                            showDeleteHistoryDialog = true
                        },
                        imageVector = Icons.Outlined.Delete,
                        itemName = stringResource(Res.string.delete_history),
                        itemColor = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}