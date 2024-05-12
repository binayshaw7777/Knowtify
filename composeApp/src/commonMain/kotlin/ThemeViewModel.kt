import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import presentation.component.Theme
import util.AppPreferences

class ThemeViewModel(
    private val applicationPreferences: AppPreferences
) : ViewModel() {

    private val _currentAppTheme = MutableStateFlow<Theme>(Theme.System)
    val currentAppTheme = _currentAppTheme.asStateFlow()

    init {
        getAppTheme()
    }

    private fun getAppTheme() = viewModelScope.launch(Dispatchers.IO) {
        applicationPreferences.onAppThemeChange.collectLatest {
            _currentAppTheme.value = it
        }
    }
}