package util

import data.model.DefinitionDTO
import data.response.DefinitionsResponse
import data.response.DictionaryResponse
import data.model.MeaningDTO
import data.response.MeaningsResponse
import data.model.WordItemDTO

fun DictionaryResponse.toWordItem() = WordItemDTO (
    id = 0,
    word = word ?: "",
    meanings = meanings?.map {
        it.toMeaning()
    } ?: emptyList(),
    phonetic = phonetic ?: ""
)

fun MeaningsResponse.toMeaning() = MeaningDTO(
    definitions = definitionDtoToDefinition(definitions?.get(0)),
    partOfSpeech = partOfSpeech ?: ""
)


fun definitionDtoToDefinition(
    definitionDto: DefinitionsResponse?
) = DefinitionDTO(
    definition = definitionDto?.definition ?: "",
    example = definitionDto?.example ?: ""
)