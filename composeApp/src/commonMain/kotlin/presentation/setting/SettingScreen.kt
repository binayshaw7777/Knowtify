package presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import knowtify.composeapp.generated.resources.Res
import knowtify.composeapp.generated.resources.about
import knowtify.composeapp.generated.resources.compose_multiplatform
import knowtify.composeapp.generated.resources.delete_history
import knowtify.composeapp.generated.resources.general
import knowtify.composeapp.generated.resources.invite_others
import knowtify.composeapp.generated.resources.licenses
import knowtify.composeapp.generated.resources.setting
import knowtify.composeapp.generated.resources.theme
import navigation.LocalNavHost
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import presentation.component.SettingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting(
    settingViewModel: SettingViewModel = koinInject()
) {

    val navController = LocalNavHost.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.setting)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEEF1F6))
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            LazyColumn {
                item {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(Res.string.general),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                item {
                    SettingItem(
                        onClick = { Logger.d("Clicked on theme") },
                        resource = Res.drawable.compose_multiplatform,
                        itemName = stringResource(Res.string.theme)
                    )
                }
                item {
                    SettingItem(
                        onClick = { Logger.d("Clicked on theme") },
                        resource = Res.drawable.compose_multiplatform,
                        itemName = stringResource(Res.string.about)
                    )
                }
                item {
                    SettingItem(
                        onClick = { Logger.d("Clicked on theme") },
                        resource = Res.drawable.compose_multiplatform,
                        itemName = stringResource(Res.string.invite_others)
                    )
                }
                item {
                    SettingItem(
                        onClick = { Logger.d("Clicked on theme") },
                        resource = Res.drawable.compose_multiplatform,
                        itemName = stringResource(Res.string.licenses)
                    )
                }
                item {
                    SettingItem(
                        onClick = {
                            settingViewModel.deleteHistory()
                            navController.popBackStack()
                        },
                        resource = Res.drawable.compose_multiplatform,
                        itemName = stringResource(Res.string.delete_history)
                    )
                }
            }
        }
    }
}