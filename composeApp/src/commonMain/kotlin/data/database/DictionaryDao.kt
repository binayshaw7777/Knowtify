package data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import data.response.Dictionary
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {

    @Upsert
    suspend fun insert(dictionary: Dictionary)

    @Delete
    suspend fun delete(dictionary: Dictionary)

    @Query("DELETE FROM dictionary WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM dictionary")
    fun getAllDictionarySearch(): Flow<List<Dictionary>>

}