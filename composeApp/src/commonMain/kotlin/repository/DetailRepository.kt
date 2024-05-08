package repository

import data.database.DictionaryDao
import data.response.Dictionary

class DetailRepository {
    suspend fun getWordMeaningFromId(dictionaryDao: DictionaryDao, id: Int): Dictionary? {
        return dictionaryDao.getDictionaryById(id)
    }
}