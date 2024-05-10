package presentation.home

import data.database.DictionaryDao
import data.network.ApiService
import data.response.DictionaryResponse
import data.model.WordItemDTO

class HomeRepository {

    suspend fun getWordMeaning(word: String): DictionaryResponse? {
        return ApiService.getWordMeaning(word).firstOrNull()
    }

    suspend fun getWordMeaningFromId(dictionaryDao: DictionaryDao, id: Int): WordItemDTO? {
        return dictionaryDao.getDictionaryById(id)
    }
}