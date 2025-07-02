package com.iskan.remote.utils

import com.iskan.domain.mapper.base.Mapper
import com.iskan.domain.model.base.DomainResource
import com.iskan.domain.model.base.DomainResourceError
import com.iskan.domain.model.base.DomainResourceErrorType
import com.iskan.remote.model.base.ErrorResponse
import com.iskan.remote.model.base.NetworkResource
import java.net.HttpURLConnection

suspend fun <V:Any, T:Any> handleApiCall(
    apiServiceTransform: suspend () -> NetworkResource<V, ErrorResponse>,
    mapperToDomain : (Mapper<V,T>)? = null,
    mapperToLocal : (Mapper<V, T>)? = null,
    localDbTransform : (suspend (result: NetworkResource<V, ErrorResponse>) -> Unit)? = null
) : DomainResource<T> {

    return when (val apiCallResult = apiServiceTransform.invoke()) {
        is NetworkResource.NetworkApiError -> {
            localDbTransform?.invoke(apiCallResult)
            val errorResponse = apiCallResult.errData
            val message: String? = errorResponse?.message

            when (apiCallResult.code) {
                HttpURLConnection.HTTP_INTERNAL_ERROR,
                HttpURLConnection.HTTP_BAD_GATEWAY,
                HttpURLConnection.HTTP_GATEWAY_TIMEOUT,
                -> DomainResource.OnError(
                    DomainResourceError(
                        if (!message.isNullOrEmpty())
                            message
                        else
                            DomainResourceErrorType.ERROR_SYSTEM.value,
                        DomainResourceErrorType.ERROR_SYSTEM
                    )
                )

                HttpURLConnection.HTTP_UNAVAILABLE -> DomainResource.OnError(
                    DomainResourceError(
                        if (!message.isNullOrEmpty())
                            message
                        else
                            DomainResourceErrorType.MAINTENANCE.value,
                        DomainResourceErrorType.MAINTENANCE
                    )
                )

                HttpURLConnection.HTTP_UNAUTHORIZED -> DomainResource.OnError(
                    DomainResourceError(
                        if (!message.isNullOrEmpty())
                            message
                        else
                            DomainResourceErrorType.UNAUTHORIZED.value,
                        DomainResourceErrorType.UNAUTHORIZED
                    )
                )

                HttpURLConnection.HTTP_NOT_FOUND -> DomainResource.OnError(
                    DomainResourceError(
                        if (!message.isNullOrEmpty())
                            message
                        else
                            DomainResourceErrorType.NOT_FOUND.value,
                        DomainResourceErrorType.NOT_FOUND
                    )
                )

                HttpURLConnection.HTTP_BAD_REQUEST -> DomainResource.OnError(
                    DomainResourceError(
                        if (!message.isNullOrEmpty())
                            message
                        else
                            DomainResourceErrorType.BAD_REQUEST.value,
                        DomainResourceErrorType.BAD_REQUEST
                    )
                )

                HttpURLConnection.HTTP_CONFLICT -> DomainResource.OnError(
                    DomainResourceError(
                        if (!message.isNullOrEmpty())
                            message
                        else
                            DomainResourceErrorType.CONFLICT.value,
                        DomainResourceErrorType.CONFLICT
                    )
                )

                else -> DomainResource.OnError(
                    DomainResourceError(
                        if (!message.isNullOrEmpty())
                            message
                        else
                            DomainResourceErrorType.UNKNOWN.value,
                        DomainResourceErrorType.UNKNOWN
                    )
                )
            }
        }

        is NetworkResource.NetworkError -> {
            localDbTransform?.invoke(apiCallResult)
            DomainResource.OnError(
                DomainResourceError(
                    apiCallResult.error?.message,
                    DomainResourceErrorType.NETWORK
                )
            )
        }

        is NetworkResource.NetworkUnknowError -> {
            localDbTransform?.invoke(apiCallResult)
            DomainResource.OnError(
                DomainResourceError(
                    apiCallResult.throwable?.message,
                    DomainResourceErrorType.UNKNOWN
                )
            )
        }

        is NetworkResource.NetworkSuccess -> {
            localDbTransform?.invoke(apiCallResult)

            apiCallResult.data?.let { v ->
                mapperToDomain?.let {
                    DomainResource.OnSuccess(mapperToDomain.to(v))
                } ?: mapperToLocal?.let {
                    DomainResource.OnSuccess(mapperToLocal.to(v))
                } ?: DomainResource.OnSuccessNoData
            } ?: run {
                DomainResource.OnSuccessNoData
            }
        }
    }

}