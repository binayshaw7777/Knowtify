package data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Entity(tableName = "dictionary")
data class Dictionary(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerialName("word") var word: String,
    @SerialName("phonetics") var phonetics: List<Phonetics>,
    @SerialName("meanings") var meanings: List<Meanings>,
    @SerialName("license") var license: License,
    @SerialName("sourceUrls") var sourceUrls: List<String>
)

@Serializable
data class License(
    @SerialName("name") var name: String,
    @SerialName("url") var url: String
)

@Serializable
data class Phonetics(
    @SerialName("text") var text: String? = null,
    @SerialName("audio") var audio: String? = null,
    @SerialName("sourceUrl") var sourceUrl: String? = null,
    @SerialName("license") val license: License? = null,
)

@Serializable
data class Definitions(
    @SerialName("definition") var definition: String,
    @SerialName("synonyms") var synonyms: List<String>,
    @SerialName("antonyms") var antonyms: List<String>,
    @SerialName("example") val example: String? = null
)

@Serializable
data class Meanings(
    @SerialName("partOfSpeech") var partOfSpeech: String,
    @SerialName("definitions") var definitions: List<Definitions>,
    @SerialName("synonyms") var synonyms: List<String>,
    @SerialName("antonyms") var antonyms: List<String>
)


// Type converter for Phonetics list
class DictionaryConverter {

    @TypeConverter
    fun fromSourceUrlsToString(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToSourceUrls(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromStringToLicense(value: String): License {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromLicenseToString(value: License): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToPhonetics(value: String): List<Phonetics> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromPhoneticsToString(value: List<Phonetics>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToMeanings(value: String): List<Meanings> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromMeaningsToString(value: List<Meanings>): String {
        return Json.encodeToString(value)
    }
}