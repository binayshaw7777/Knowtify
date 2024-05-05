package data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dictionary(
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
    @SerialName("audio") var audio: String,
    @SerialName("sourceUrl") var sourceUrl: String,
)

@Serializable
data class Definitions(
    @SerialName("definition") var definition: String,
    @SerialName("synonyms") var synonyms: List<String>,
    @SerialName("antonyms") var antonyms: List<String>
)

@Serializable
data class Meanings(
    @SerialName("partOfSpeech") var partOfSpeech: String,
    @SerialName("definitions") var definitions: List<Definitions>,
    @SerialName("synonyms") var synonyms: List<String>,
    @SerialName("antonyms") var antonyms: List<String>
)

@Serializable
data class DictionaryResponse(
    val dictionary: List<Dictionary>
)