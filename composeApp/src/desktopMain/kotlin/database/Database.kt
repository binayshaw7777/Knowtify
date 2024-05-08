package database

import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.DictionaryDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<DictionaryDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "dictionary.db")
    return Room.databaseBuilder<DictionaryDatabase>(
        name = dbFile.absolutePath,
    )
}