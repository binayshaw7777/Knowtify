package database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.DictionaryDatabase
import platform.Foundation.NSHomeDirectory
import data.database.instantiateImpl

fun getDictionaryDatabase(): RoomDatabase.Builder<DictionaryDatabase> {
    val dbFile = NSHomeDirectory() + "/dictionary.db"
    return Room.databaseBuilder<DictionaryDatabase>(
        name = dbFile,
        factory = { DictionaryDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
}