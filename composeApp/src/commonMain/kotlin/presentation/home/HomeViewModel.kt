package presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import data.database.DictionaryDao
import data.database.DictionaryDatabase
import data.model.WordItemDTO
import data.network.ApiStatus
import data.response.DictionaryResponse
import data.response.FailedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.home.states.HomeScreenState
import presentation.home.states.HomeState
import util.toWordItem

class HomeViewModel(
    private val repository: HomeRepository,
    dictionaryDatabase: DictionaryDatabase
) : ViewModel() {

    private val dictionaryDao: DictionaryDao = dictionaryDatabase.dictionaryDao()

    private val _insertedDictionary: MutableStateFlow<WordItemDTO?> =
        MutableStateFlow(null)
    val insertedDictionary get() = _insertedDictionary.asStateFlow()

    private val _dictionaryDatabase: MutableStateFlow<List<WordItemDTO>> =
        MutableStateFlow(emptyList())
    val dictionaryDatabase get() = _dictionaryDatabase.asStateFlow()


    private val _homeState = MutableStateFlow(HomeState())

    private val _homeViewState: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(HomeScreenState.Clear)
    val homeViewState = _homeViewState.asStateFlow()



    private suspend fun insertDictionary(dictionary: DictionaryResponse) {
        val id = repository.insertDictionary(dictionary.toWordItem(), dictionaryDao)
        getWordMeaningFromId(id)
    }

    fun getAllDictionarySearch() = viewModelScope.launch(Dispatchers.IO) {
        _dictionaryDatabase.value = dictionaryDao.getAllDictionarySearch()
    }

    private suspend fun getWordMeaningFromId(id: Int) {
        repository.getWordMeaningFromId(dictionaryDao, id)
        _insertedDictionary.value = dictionaryDao.getDictionaryById(id)
    }

    private suspend fun wordExists(word: String): WordItemDTO? {
        return repository.searchDictionary(word, dictionaryDao)
    }

    fun clearStates() {
        Logger.d("Clearing states")
        _insertedDictionary.value = null
        _homeViewState.value = HomeScreenState.Clear
    }

    suspend fun getDictionary(word: String) {
        Logger.d("Entered getDictionary")
        val wordExists = wordExists(word)
        if (wordExists != null) {
            _homeViewState.value = HomeScreenState.Success(null)
            _insertedDictionary.value = wordExists
        } else {
            getDictionaryFromApi(word)
        }
    }

    private suspend fun getDictionaryFromApi(word: String) {
        try {
            repository.getWordMeaning(word).collect { response ->
                when (response.status) {
                    ApiStatus.LOADING -> {
                        _homeState.update { it.copy(isLoading = true) }
                    }

                    ApiStatus.SUCCESS -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                responseData = response.data?.firstOrNull()
                            )
                        }
                        response.data?.firstOrNull()?.let { nonNullResponse ->
                            insertDictionary(nonNullResponse)
                        }
                    }

                    ApiStatus.FAILED -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                errorData = response.fail
                            )
                        }
                    }
                }
                _homeViewState.value = _homeState.value.toUiState()
            }
        } catch (e: Exception) {
            Logger.d("Error: ${e.message}")
            _homeViewState.value =
                HomeScreenState.Error(FailedResponse(message = e.message.toString()))
        }
    }
}