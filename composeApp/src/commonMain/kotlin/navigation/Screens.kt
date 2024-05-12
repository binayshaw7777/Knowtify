package navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object Setting : Screens("setting")
    data object Detail : Screens("detail")
}