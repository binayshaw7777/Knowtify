package presentation.detail

import androidx.lifecycle.ViewModel
import data.database.DictionaryDao
import data.database.DictionaryDatabase
import data.response.Dictionary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import repository.DetailRepository

class DetailViewModel(
    private val detailRepository: DetailRepository,
    dictionaryDatabase: DictionaryDatabase
) : ViewModel() {

    private val dictionaryDao: DictionaryDao = dictionaryDatabase.dictionaryDao()

    private val _wordMeaning = MutableStateFlow<Dictionary?>(null)
    val wordMeaning: StateFlow<Dictionary?> get() = _wordMeaning

    suspend fun getWordMeaningFromId(id: Int) {
        _wordMeaning.value = detailRepository.getWordMeaningFromId(dictionaryDao, id)
    }

}