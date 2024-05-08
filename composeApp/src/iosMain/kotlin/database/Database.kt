package database

import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.DictionaryDatabase
import data.database.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getDictionaryDatabase(): RoomDatabase.Builder<DictionaryDatabase> {
    val dbFile = NSHomeDirectory() + "/dictionary.db"
    return Room.databaseBuilder<DictionaryDatabase>(
        name = dbFile,
        factory = { DictionaryDatabase::class.instantiateImpl() }
    )
}