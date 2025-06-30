package com.iskan.jello.utils.retrofit

import com.iskan.remote.model.base.NetworkResource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Converter

abstract class NetworkResourceCall<V: Any, T: Any>(
    private val proxy: Call<V>,
    private val errorConverter: Converter<ResponseBody, T>
) : Call<NetworkResource<V, T>> {
}