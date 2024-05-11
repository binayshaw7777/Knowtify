package data.network


import data.response.DictionaryResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import util.Constant.BASE_URL
import util.Constant.TIMEOUT

object ApiService {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient {
        defaultRequest {
            url("$BASE_URL/")
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = false
                coerceInputValues = true
                explicitNulls = true
            })
        }

        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }

        install(HttpCallValidator) {
            handleResponseExceptionWithRequest { cause, _ -> println("exception $cause") }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            filter { request ->
                request.url.host.contains("api.dictionaryapi.dev")
            }
        }
    }

    suspend fun getWordMeaning(word: String): List<DictionaryResponse> {
        val response = client.get(word)
        co.touchlab.kermit.Logger.d("Response: ${response.body<String>()}")
        return response.body()
    }

}