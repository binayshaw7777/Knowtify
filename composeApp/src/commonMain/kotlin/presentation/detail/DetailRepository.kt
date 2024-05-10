package presentation.detail

import data.database.DictionaryDao
import data.model.WordItemDTO

class DetailRepository {
    suspend fun getWordMeaningFromId(dictionaryDao: DictionaryDao, id: Int): WordItemDTO? {
        return dictionaryDao.getDictionaryById(id)
    }
}