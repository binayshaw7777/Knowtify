package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.DictionaryDatabase

fun getDictionaryDatabase(): DictionaryDatabase {
    val dbFile = NSHomeDirectory() + "/dictionary.db"
    return Room.databaseBuilder<DictionaryDatabase>(
        name = dbFile,
        factory = { DictionaryDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}