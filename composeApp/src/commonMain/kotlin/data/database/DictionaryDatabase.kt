package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.response.Dictionary
import data.response.DictionaryConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [Dictionary::class], version = 1)
@TypeConverters(DictionaryConverter::class)
abstract class DictionaryDatabase: RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<DictionaryDatabase>
): DictionaryDatabase {
    return builder
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}