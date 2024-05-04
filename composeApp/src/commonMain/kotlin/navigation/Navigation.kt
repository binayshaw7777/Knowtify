package navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import presentation.home.HomeScreen
import presentation.setting.Setting


val LocalNavHost = staticCompositionLocalOf<NavHostController> {
    error("No Parameter is available")
}

@Composable
fun Navigation() {

    val navController: NavHostController = rememberNavController()

    CompositionLocalProvider(LocalNavHost provides navController) {
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screens.Home.route,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                composable(route = Screens.Home.route) {
                    HomeScreen()
                }

                composable(route = Screens.Setting.route) {
                    Setting()
                }
            }
        }
    }

}