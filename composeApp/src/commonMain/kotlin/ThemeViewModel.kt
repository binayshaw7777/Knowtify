import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import util.AppPreferences

class ThemeViewModel(
    private val applicationPreferences: AppPreferences
) : ViewModel(){

    private val _currentTheme = MutableStateFlow<Boolean>(false)
    val currentTheme = _currentTheme.asStateFlow()

    init {
        getTheme()
    }

    private fun getTheme() = viewModelScope.launch(Dispatchers.IO) {
        applicationPreferences.onDarkModeChange.collectLatest {
            _currentTheme.value = it
        }
    }
}