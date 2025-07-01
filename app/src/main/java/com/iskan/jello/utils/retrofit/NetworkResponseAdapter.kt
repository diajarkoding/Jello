package com.iskan.jello.utils.retrofit
import com.iskan.remote.model.base.NetworkResource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResponseAdapter<S:Any, E:Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<NetworkResource<S, E>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResource<S, E>> {
        return NetworkResourceCall(call, errorBodyConverter)
    }
}