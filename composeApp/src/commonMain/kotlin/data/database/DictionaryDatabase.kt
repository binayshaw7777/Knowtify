package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.response.Dictionary
import data.response.DictionaryConverter

@Database(entities = [Dictionary::class], version = 1)
@TypeConverters(DictionaryConverter::class)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

}