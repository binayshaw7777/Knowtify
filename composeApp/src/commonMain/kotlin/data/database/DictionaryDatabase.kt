package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import data.model.TypeConverter
import data.model.WordItemDTO

@Database(entities = [WordItemDTO::class], version = 2)
@TypeConverters(TypeConverter::class)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

    companion object {

        var query = "ALTER TABLE dictionary DROP COLUMN license; " +
                "DROP COLUMN sourceUrls; " +
                "RENAME COLUMN phonetics TO phonetic; " +
                "ADD COLUMN phonetic TEXT NOT NULL DEFAULT 'undefined';"

        val MIGRATION_1_2: Migration = object : Migration(2, 3) {
            override fun migrate(connection: SQLiteConnection) {
                connection.execSQL(query)
            }
        }
    }
}