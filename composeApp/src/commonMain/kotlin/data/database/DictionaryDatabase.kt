package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.model.TypeConverter
import data.model.WordItemDTO

@Database(entities = [WordItemDTO::class], version = 2)
@TypeConverters(TypeConverter::class)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

}