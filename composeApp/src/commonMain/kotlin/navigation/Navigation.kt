package navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import presentation.detail.DetailScreen
import presentation.home.HomeScreen
import presentation.setting.Setting
import util.FadeIn
import util.FadeOut


val LocalNavHost = staticCompositionLocalOf<NavHostController> {
    error("No Parameter is available")
}

@Composable
fun Navigation() {

    val navController: NavHostController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    CompositionLocalProvider(LocalNavHost provides navController) {
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screens.Home.route,
                enterTransition = { FadeIn },
                exitTransition = { FadeOut },
                modifier = Modifier
                    .fillMaxSize()
            ) {

                composable(route = Screens.Home.route) {
                    HomeScreen()
                }

                composable(route = Screens.Setting.route) {
                    Setting()
                }

                composable(
                    route = "${Screens.Detail.route}/{meaningId}",
                    arguments = listOf(navArgument("meaningId") { type = IntType })
                ) {

                    val meaningId: Int = backStackEntry.value?.arguments?.getInt("meaningId") ?: -1

                    DetailScreen(meaningId = meaningId)
                }
            }
        }
    }

}