package data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Entity(tableName = "dictionary")
data class WordItemDTO(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val meanings: List<MeaningDTO>? = null,
    val phonetic: String? = null,
    val word: String? = null
)

@Serializable
data class MeaningDTO(
    val definitions: DefinitionDTO? = null,
    val partOfSpeech: String? = null
)

@Serializable
data class DefinitionDTO(
    val definition: String? = null,
    val example: String? = null
)


// Type converter for Phonetics list
class TypeConverter {

    @TypeConverter
    fun fromDefinitionDTOToString(value: DefinitionDTO): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToDefinitionDTO(value: String): DefinitionDTO {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromStringToMeanings(value: String): List<MeaningDTO> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromMeaningsToString(value: List<MeaningDTO>): String {
        return Json.encodeToString(value)
    }
}