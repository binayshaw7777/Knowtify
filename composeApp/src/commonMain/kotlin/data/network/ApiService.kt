package data.network

import data.response.DictionaryResponse
import data.response.FailedResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

object ApiService {

    suspend fun HttpClient.getWordMeaning(word: String): Flow<ApiResult<List<DictionaryResponse>?, FailedResponse>> {
        return toResultFlow {
            val response = this.get("https://api.dictionaryapi.dev/api/v2/entries/en_US/$word")
                .body<List<DictionaryResponse>>()
            ApiResult.Success(response)
        }
    }
}