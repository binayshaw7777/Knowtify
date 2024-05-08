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

    private val _insertedDictionary: MutableStateFlow<Dictionary?> =
        MutableStateFlow(null)
    val insertedDictionary: StateFlow<Dictionary?> get() = _insertedDictionary

    private val _dictionaryDatabase: MutableStateFlow<List<Dictionary>> =
        MutableStateFlow(emptyList())
    val dictionaryDatabase: StateFlow<List<Dictionary>> get() = _dictionaryDatabase

    suspend fun insertDictionary(dictionary: Dictionary) {
        val id = dictionaryDao.insert(dictionary)
        getWordMeaningFromId(id.toInt())
    }

    fun getAllDictionarySearch() = viewModelScope.launch(Dispatchers.IO) {
        _dictionaryDatabase.value = dictionaryDao.getAllDictionarySearch()
    }

    suspend fun getWordMeaningFromId(id: Int) {
        repository.getWordMeaningFromId(dictionaryDao, id)
        _insertedDictionary.value = dictionaryDao.getDictionaryById(id)
    }

    fun setUiState(dictionary: Dictionary?) {
        _uiState.value = dictionary
    }

    fun clearStates() {
        Logger.d("Clearing states")
        _uiState.value = null
        _insertedDictionary.value = null
    }

    suspend fun getDictionary(word: String) {
        Logger.d("Entered getDictionary")
        setUiState(getDictionaryFromApi(word))
        insertDictionary(uiState.value!!)
    }

    private suspend fun getDictionaryFromApi(word: String): Dictionary? {
        val response = repository.getWordMeaning(word)
        Logger.d("Response: $response")
        return response
    }
}