package di

import ThemeViewModel
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.DictionaryDatabase
import data.database.DictionaryDatabase.Companion.MIGRATION_1_2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import presentation.detail.DetailViewModel
import presentation.home.HomeViewModel
import presentation.setting.SettingViewModel
import presentation.detail.DetailRepository
import presentation.home.HomeRepository
import presentation.setting.SettingRepository
import util.coreComponent
import util.viewModelDefinition

fun appModule(databaseBuilder: RoomDatabase.Builder<DictionaryDatabase>) = module {

    single { HomeRepository() }
    single { DetailRepository() }
    single { SettingRepository() }
    single { coreComponent.appPreferences }
    single {
        databaseBuilder
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    viewModelDefinition { HomeViewModel(get(), get()) }
    viewModelDefinition { DetailViewModel(get(), get()) }
    viewModelDefinition { SettingViewModel(get(), get(), get()) }
    viewModelDefinition { ThemeViewModel(get()) }
}