package repository

import data.network.ApiService
import data.response.Dictionary
import data.response.DictionaryResponse

class HomeRepository {

    suspend fun getWordMeaning(word: String): List<Dictionary> {
        return ApiService.getWordMeaning(word)
    }
}