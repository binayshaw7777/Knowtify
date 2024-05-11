package presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.database.DictionaryDao
import data.database.DictionaryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import util.ApplicationComponent

class SettingViewModel(
    private val settingRepository: SettingRepository,
    dictionaryDatabase: DictionaryDatabase
) : ViewModel() {

    private val appPreferences = ApplicationComponent.coreComponent.appPreferences

    private val dictionaryDao: DictionaryDao = dictionaryDatabase.dictionaryDao()

    private val _isDarkModeEnabled = MutableStateFlow<Boolean>(false)
    val isDarkModeEnabled = _isDarkModeEnabled.asStateFlow()

    fun deleteHistory() = viewModelScope.launch(Dispatchers.IO) {
        settingRepository.deleteAllDictionarySearch(dictionaryDao)
    }

    private fun isDarkModeEnabled() = viewModelScope.launch(Dispatchers.IO) {
        _isDarkModeEnabled.value = appPreferences.isDarkModeEnabled()
    }

    fun changeDarkMode(isEnabled: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        appPreferences.changeDarkMode(isEnabled)
        _isDarkModeEnabled.value = isEnabled
    }
}