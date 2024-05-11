package di

import ThemeViewModel
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.ktor.client.plugins.logging.DEFAULT
import data.database.DictionaryDatabase
import data.database.DictionaryDatabase.Companion.MIGRATION_1_2
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import kotlinx.coroutines.Dispatchers
import io.ktor.client.plugins.logging.Logger
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import presentation.detail.DetailViewModel
import presentation.home.HomeViewModel
import presentation.setting.SettingViewModel
import presentation.detail.DetailRepository
import presentation.home.HomeRepository
import presentation.setting.SettingRepository
import util.Constant
import util.coreComponent
import util.viewModelDefinition

fun appModule(databaseBuilder: RoomDatabase.Builder<DictionaryDatabase>) = module {

    single {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                filter { request ->
                    request.url.host.contains("api.dictionaryapi.dev")
                }
            }
            defaultRequest {
                url("${Constant.BASE_URL}/")
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
                connectTimeoutMillis = Constant.TIMEOUT
                requestTimeoutMillis = Constant.TIMEOUT
                socketTimeoutMillis = Constant.TIMEOUT
            }

            install(HttpCallValidator) {
                handleResponseExceptionWithRequest { cause, _ -> println("exception $cause") }
            }
        }
    }

    single {
        databaseBuilder
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single { HomeRepository(get()) }
    single { DetailRepository() }
    single { SettingRepository() }
    single { coreComponent.appPreferences }

    viewModelDefinition { HomeViewModel(get(), get()) }
    viewModelDefinition { DetailViewModel(get(), get()) }
    viewModelDefinition { SettingViewModel(get(), get(), get()) }
    viewModelDefinition { ThemeViewModel(get()) }
}