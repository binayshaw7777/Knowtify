package data.network

// https://github.com/RajaNimit27/ComposeMultiplatform_Ktor_client_Android_iOS_Desktop/blob/master/composeApp/src/commonMain/kotlin/network/NetworkResult.kt

sealed class ApiResult<T, E>(
    val status: ApiStatus,
    val data: T?,
    val fail: E?,
    val message: String?
) {

    data class Success<T, E>(val _data: T?) :
        ApiResult<T, E>(status = ApiStatus.SUCCESS, data = _data, fail = null, message = null)

    data class Error<T, E>(val _error: E?, val exception: String) :
        ApiResult<T, E>(status = ApiStatus.FAILED, data = null, fail = _error, message = exception)

    data class Loading<T, E>(val isLoading: Boolean) :
        ApiResult<T, E>(status = ApiStatus.LOADING, data = null, fail = null, message = null)
}

enum class ApiStatus {
    SUCCESS,
    FAILED,
    LOADING,
}