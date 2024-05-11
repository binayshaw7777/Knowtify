package data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FailedResponse(
    @SerialName("title") var title: String? = null,
    @SerialName("message") var message: String? = null,
    @SerialName("resolution") var resolution: String? = null
)