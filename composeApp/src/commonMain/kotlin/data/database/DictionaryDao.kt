package data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import data.model.WordItemDTO

@Dao
interface DictionaryDao {

    @Upsert
    suspend fun insert(dictionary: WordItemDTO): Long

    @Query("SELECT * FROM dictionary WHERE id = :id")
    suspend fun getDictionaryById(id: Int): WordItemDTO?

    @Delete
    suspend fun delete(dictionary: WordItemDTO)

    @Query("DELETE FROM dictionary WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("SELECT * FROM dictionary")
    suspend fun getAllDictionarySearch(): List<WordItemDTO>

    @Query("DELETE FROM dictionary")
    suspend fun deleteAll()

    @Query("SELECT * FROM dictionary WHERE word LIKE :queryWord")
    suspend fun searchDictionary(queryWord: String): List<WordItemDTO>
}