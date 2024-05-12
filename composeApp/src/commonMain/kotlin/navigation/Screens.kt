package navigation

import util.Constant.DETAIL
import util.Constant.HOME
import util.Constant.SETTING

sealed class Screens(val route: String) {
    data object Home : Screens(HOME)
    data object Setting : Screens(SETTING)
    data object Detail : Screens(DETAIL)
}