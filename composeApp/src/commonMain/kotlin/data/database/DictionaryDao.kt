package data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import data.response.Dictionary

@Dao
interface DictionaryDao {

    @Upsert
    suspend fun insert(dictionary: Dictionary): Long

    @Query("SELECT * FROM dictionary WHERE id = :id")
    suspend fun getDictionaryById(id: Int): Dictionary?

    @Delete
    suspend fun delete(dictionary: Dictionary)

    @Query("DELETE FROM dictionary WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("SELECT * FROM dictionary")
    suspend fun getAllDictionarySearch(): List<Dictionary>

}