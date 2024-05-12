package presentation.detail

import androidx.lifecycle.ViewModel
import data.database.DictionaryDao
import data.database.DictionaryDatabase
import data.model.WordItemDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel(
    private val detailRepository: DetailRepository,
    dictionaryDatabase: DictionaryDatabase
) : ViewModel() {

    private val dictionaryDao: DictionaryDao = dictionaryDatabase.dictionaryDao()

    private val _wordMeaning = MutableStateFlow<WordItemDTO?>(null)
    val wordMeaning: StateFlow<WordItemDTO?> get() = _wordMeaning

    suspend fun getWordMeaningFromId(id: Int) {
        _wordMeaning.value = detailRepository.getWordMeaningFromId(dictionaryDao, id)
    }

}