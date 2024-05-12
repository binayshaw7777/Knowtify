package presentation.home

import data.database.DictionaryDao
import data.response.DictionaryResponse
import data.model.WordItemDTO
import data.network.ApiResult
import data.network.ApiService.getWordMeaning
import data.response.FailedResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class HomeRepository(
    private val httpClient: HttpClient
) {
    suspend fun getWordMeaning(word: String): Flow<ApiResult<List<DictionaryResponse>?, FailedResponse>> {
        return httpClient.getWordMeaning(word)
    }

    suspend fun getWordMeaningFromId(dictionaryDao: DictionaryDao, id: Int): WordItemDTO? {
        return dictionaryDao.getDictionaryById(id)
    }

    suspend fun insertDictionary(wordItem: WordItemDTO, dictionaryDao: DictionaryDao) : Int {
        val id = dictionaryDao.insert(wordItem)
        return id.toInt()
    }

    suspend fun searchDictionary(queryWord: String, dictionaryDao: DictionaryDao): WordItemDTO? {
        return dictionaryDao.searchDictionary(queryWord).firstOrNull()
    }
}