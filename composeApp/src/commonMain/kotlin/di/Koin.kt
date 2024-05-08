package di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.DictionaryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import presentation.detail.DetailViewModel
import presentation.home.HomeViewModel
import repository.DetailRepository
import repository.HomeRepository
import util.viewModelDefinition

fun appModule(databaseBuilder: RoomDatabase.Builder<DictionaryDatabase>) = module {

    single { HomeRepository() }
    single { DetailRepository() }
    single {
        databaseBuilder.fallbackToDestructiveMigrationOnDowngrade(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO).build()
    }

    viewModelDefinition { HomeViewModel(get(), get()) }
    viewModelDefinition { DetailViewModel(get(), get()) }
}