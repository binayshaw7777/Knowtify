package repository

import data.network.ApiService
import data.response.Dictionary

class HomeRepository {

    suspend fun getWordMeaning(word: String): Dictionary? {
        return ApiService.getWordMeaning(word).firstOrNull()
    }
}