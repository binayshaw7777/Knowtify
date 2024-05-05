package presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import data.network.Resource
import data.response.Dictionary
import data.response.DictionaryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import repository.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<List<Dictionary>> =
        MutableStateFlow(emptyList())
    val uiState: StateFlow<List<Dictionary>> get() = _uiState

    fun getDictionary(word: String) = viewModelScope.launch(Dispatchers.IO) {
        Logger.d("Entered getDictionary")
        _uiState.value = getDictionaryFromApi(word)
    }

    private suspend fun getDictionaryFromApi(word: String): List<Dictionary> {
        val response = repository.getWordMeaning(word)
        Logger.d("Response: $response")
        return response
    }
}

data class HomeState(
    val dictionaryResponse: Resource<DictionaryResponse>? = null
)