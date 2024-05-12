package data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryResponse(
    @SerialName("word") var word: String? = null,
    @SerialName("phonetic") var phonetic: String? = null,
    @SerialName("meanings") var meanings: List<MeaningsResponse>? = null,
)

@Serializable
data class DefinitionsResponse(
    @SerialName("definition") var definition: String? = null,
    @SerialName("example") val example: String? = null
)

@Serializable
data class MeaningsResponse(
    @SerialName("partOfSpeech") var partOfSpeech: String? = null,
    @SerialName("definitions") var definitions: List<DefinitionsResponse>? = null
)