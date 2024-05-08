package repository

import data.database.DictionaryDao
import data.network.ApiService
import data.response.Dictionary

class HomeRepository {

    suspend fun getWordMeaning(word: String): Dictionary? {
        return ApiService.getWordMeaning(word).firstOrNull()
    }

    suspend fun getWordMeaningFromId(dictionaryDao: DictionaryDao, id: Int): Dictionary? {
        return dictionaryDao.getDictionaryById(id)
    }
}