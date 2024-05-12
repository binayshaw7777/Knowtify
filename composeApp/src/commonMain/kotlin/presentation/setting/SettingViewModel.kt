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
import presentation.component.Theme
import util.AppPreferences

class SettingViewModel(
    private val settingRepository: SettingRepository,
    dictionaryDatabase: DictionaryDatabase,
    private val appPreferences: AppPreferences
) : ViewModel() {


    private val dictionaryDao: DictionaryDao = dictionaryDatabase.dictionaryDao()

    private val _appTheme = MutableStateFlow(Theme.System)
    val appTheme = _appTheme.asStateFlow()

    fun deleteHistory() = viewModelScope.launch(Dispatchers.IO) {
        settingRepository.deleteAllDictionarySearch(dictionaryDao)
    }

    init {
        getAppTheme()
    }

    private fun getAppTheme() = viewModelScope.launch(Dispatchers.IO) {
        _appTheme.value = appPreferences.getAppTheme()
    }

    fun changeAppTheme(theme: Theme) = viewModelScope.launch(Dispatchers.IO) {
        appPreferences.changeAppTheme(theme)
        _appTheme.value = theme
    }
}