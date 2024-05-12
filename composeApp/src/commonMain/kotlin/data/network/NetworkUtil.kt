package data.network

import co.touchlab.kermit.Logger
import data.response.FailedResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> toResultFlow(call: suspend () -> ApiResult<T, FailedResponse?>) : Flow<ApiResult<T, FailedResponse>> {
    return flow {
        emit(ApiResult.Loading(true))
        val c = call.invoke()
        c.let { response ->
            try {
                println("response${response.data}")
                emit(ApiResult.Success(response.data))
            } catch (e: Exception) {
                Logger.d("Error: ${e.message}")
                emit(ApiResult.Error(response.data as FailedResponse, e.toString()))
            }
        }
    }
}