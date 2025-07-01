package com.iskan.jello.utils.retrofit

import com.iskan.remote.model.base.NetworkResource
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        check(returnType is ParameterizedType) {
            "return type must be paramaterized as Call<NetworkResource<V>> or Call<NetworkResource<out V>>"
        }

        val responseType = getParameterUpperBound(0, returnType)

        if (getRawType(responseType) != NetworkResource::class.java) {
            return null
        }

        check(responseType is ParameterizedType) {
            "NetworkResource must be paramaterized as NetworkResource<V> or NetworkResource<out V>"
        }

        val successBodyType = getParameterUpperBound(0, responseType)
        val errorBodyType = getParameterUpperBound(1, responseType)
        val errorBodyConverter = retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return NetworkResponseAdapter<Any, Any>(successBodyType, errorBodyConverter)

    }
}