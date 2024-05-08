package data.network

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


sealed class Resource<T> {
    class Loading<T>(val msg: String) : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failed<T>(val exception: Exception) : Resource<T>()
    class Clear<T>() : Resource<T>()

    companion object {
        fun <T> loading(msg: String = "Please Wait") = Loading<T>(msg)
        fun <T> success(data: T) = Success(data)
        fun <T> failed(e: Exception) = Failed<T>(e)
        fun <T> clear() = Clear<T>()
    }
}

fun <T> Resource<T>.exceptionOrNull(): Exception? {
    return (this as? Resource.Failed)?.exception
}

fun <T> Resource<T>.dataOrNull(): T? {
    return (this as? Resource.Success)?.data
}

inline fun <S, T> Resource<T>.map(
    crossinline transform: (T) -> S
): Resource<S> {
    return when (this) {
        is Resource.Failed -> Resource.failed(exception)
        is Resource.Loading -> Resource.loading(msg)
        is Resource.Success -> Resource.success(transform(data))
        is Resource.Clear -> Resource.clear()
    }
}

/*
* Function to call a code block which can produce data / throw Exception and Encapsulate both in Result Object
* */
@Suppress("InlineResource")
suspend inline fun <T> Resource(
    dispatcher: CoroutineDispatcher? = null,
    crossinline block: suspend () -> T
): Resource<T> {
    return try {
        if (dispatcher != null) {
            withContext(dispatcher) {
                Resource.success(block())
            }
        } else Resource.success(block())
    } catch (e: Exception) {
//        if (BuildConfig.DEBUG) {
            Logger.d(e.message.toString())
//        }

        // Add Basic Generic Exception Handling If Needed
        Resource.failed(e)
    }
}