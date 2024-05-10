package presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.database.DictionaryDao
import data.database.DictionaryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import repository.SettingRepository

class SettingViewModel(
    private val settingRepository: SettingRepository,
    dictionaryDatabase: DictionaryDatabase
) : ViewModel() {

    private val dictionaryDao: DictionaryDao = dictionaryDatabase.dictionaryDao()

    fun deleteHistory() = viewModelScope.launch(Dispatchers.IO) {
        settingRepository.deleteAllDictionarySearch(dictionaryDao)
    }
}