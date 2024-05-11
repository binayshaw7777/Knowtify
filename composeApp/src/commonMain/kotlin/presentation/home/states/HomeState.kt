package presentation.home.states

import data.response.DictionaryResponse
import data.response.FailedResponse

data class HomeState(
    val isLoading: Boolean = false,
    val errorData: FailedResponse? = null,
    val responseData: DictionaryResponse? = null
) {
    fun toUiState(): HomeScreenState {
        return if (isLoading) {
            HomeScreenState.Loading
        } else if (errorData?.message?.isNotEmpty() == true) {
            HomeScreenState.Error(errorData)
        } else {
            HomeScreenState.Success(responseData!!)
        }
    }
}