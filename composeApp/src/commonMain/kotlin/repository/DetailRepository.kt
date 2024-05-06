package repository

import data.database.DictionaryDao
import data.response.Dictionary
import kotlinx.coroutines.flow.Flow

class DetailRepository {
    fun getWordMeaningFromId(dictionaryDao: DictionaryDao, id: Int): Flow<Dictionary?> {
        return dictionaryDao.getDictionaryById(id)
    }
}