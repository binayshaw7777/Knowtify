package repository

import data.database.DictionaryDao

class SettingRepository {
    suspend fun deleteAllDictionarySearch(dictionaryDao: DictionaryDao) =
        dictionaryDao.deleteAll()
}