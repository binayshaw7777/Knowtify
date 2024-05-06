package presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.database.DictionaryDao
import data.response.Dictionary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import repository.DetailRepository

class DetailViewModel(
    private val detailRepository: DetailRepository,
    private val dictionaryDao: DictionaryDao
) : ViewModel() {

    private val _wordMeaning = MutableStateFlow<Dictionary?>(null)
    val wordMeaning: StateFlow<Dictionary?> get() = _wordMeaning

    fun getWordMeaningFromId(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        detailRepository.getWordMeaningFromId(dictionaryDao, id).collectLatest {
            _wordMeaning.value = it
        }
    }

}