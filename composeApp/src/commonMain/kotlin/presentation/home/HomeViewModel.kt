package presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import data.database.DictionaryDao
import data.response.Dictionary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import repository.HomeRepository

class HomeViewModel(
    private val repository: HomeRepository,
    private val dictionaryDao: DictionaryDao
) : ViewModel() {

    private val _uiState: MutableStateFlow<Dictionary?> =
        MutableStateFlow(null)
    val uiState: StateFlow<Dictionary?> get() = _uiState

    fun insertDictionary(dictionary: Dictionary) = viewModelScope.launch(Dispatchers.IO) {
        dictionaryDao.insert(dictionary)
    }

    fun getAllDictionarySearch() = viewModelScope.launch(Dispatchers.IO) {
        dictionaryDao.getAllDictionarySearch()
    }

    fun setUiState(dictionary: Dictionary?) {
        _uiState.value = dictionary
    }

    fun getDictionary(word: String) = viewModelScope.launch(Dispatchers.IO) {
        Logger.d("Entered getDictionary")
        setUiState(getDictionaryFromApi(word))
    }

    private suspend fun getDictionaryFromApi(word: String): Dictionary? {
        val response = repository.getWordMeaning(word)
        Logger.d("Response: $response")
        return response
    }
}