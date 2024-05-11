package data.network

// Src: https://github.com/Kashif-E/KMPMovies/blob/master/MoviesApp/src/commonMain/kotlin/com/kashif/common/data/paging/Result.kt

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: String) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this.map<T, Result<T>> { Result.Success(it) }.onStart { emit(Result.Loading) }
        .catch { exception ->
            emit(Result.Error(exception.message ?: "Something went wrong, please try again."))
        }
}