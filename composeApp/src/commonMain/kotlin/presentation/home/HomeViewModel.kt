package presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import data.database.DictionaryDao
import data.database.DictionaryDatabase
import data.response.DictionaryResponse
import data.model.WordItemDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import util.toWordItem

class HomeViewModel(
    private val repository: HomeRepository,
    dictionaryDatabase: DictionaryDatabase
) : ViewModel() {

    private val dictionaryDao: DictionaryDao = dictionaryDatabase.dictionaryDao()

    private val _uiState: MutableStateFlow<DictionaryResponse?> =
        MutableStateFlow(null)
    val uiState: StateFlow<DictionaryResponse?> get() = _uiState

    private val _insertedDictionary: MutableStateFlow<WordItemDTO?> =
        MutableStateFlow(null)
    val insertedDictionary: StateFlow<WordItemDTO?> get() = _insertedDictionary

    private val _dictionaryDatabase: MutableStateFlow<List<WordItemDTO>> =
        MutableStateFlow(emptyList())
    val dictionaryDatabase: StateFlow<List<WordItemDTO>> get() = _dictionaryDatabase

    private suspend fun insertDictionary(dictionary: DictionaryResponse) {
        val id = dictionaryDao.insert(dictionary.toWordItem())
        getWordMeaningFromId(id.toInt())
    }

    fun getAllDictionarySearch() = viewModelScope.launch(Dispatchers.IO) {
        _dictionaryDatabase.value = dictionaryDao.getAllDictionarySearch()
    }

    private suspend fun getWordMeaningFromId(id: Int) {
        repository.getWordMeaningFromId(dictionaryDao, id)
        _insertedDictionary.value = dictionaryDao.getDictionaryById(id)
    }

    private fun setUiState(dictionary: DictionaryResponse?) {
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

    private suspend fun getDictionaryFromApi(word: String): DictionaryResponse? {
        val response = repository.getWordMeaning(word)
        Logger.d("Response: $response")
        return response
    }
}