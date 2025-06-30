package com.iskan.remote.model.base

import java.io.IOException

sealed class NetworkResource<out V: Any, out T: Any> {
    /**
     * Represents a successful network response.
     */
    data class NetworkSuccess<V: Any>(val data: V): NetworkResource<V, Nothing>()

    /**
     * Indicates a network API error, for example, a 401 error.
     */
    data class NetworkApiError<T: Any>(val errorData: T?, val code: Int): NetworkResource<Nothing, T>()

    /**
     * Represents a network issue, such as no internet connection.
     */
    data class NetworkIssue(val error: IOException): NetworkResource<Nothing, Nothing>()

    /**
     * Represents an error when parsing JSON data.
     */
    data class NetworkUnknownError(val throwable: Throwable): NetworkResource<Nothing, Nothing>()
}