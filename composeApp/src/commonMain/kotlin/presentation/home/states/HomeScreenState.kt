package presentation.home.states

import data.response.DictionaryResponse
import data.response.FailedResponse

sealed class HomeScreenState {
    data object Loading : HomeScreenState()
    data class Error(val errorResponse: FailedResponse) : HomeScreenState()
    data class Success(val responseData: DictionaryResponse?) : HomeScreenState()
    data object Clear : HomeScreenState()
}