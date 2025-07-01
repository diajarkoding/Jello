package com.iskan.remote.services

import com.iskan.remote.model.base.ErrorResponse
import com.iskan.remote.model.base.NetworkResource
import com.iskan.remote.model.home.JelloHomeResponse
import com.iskan.remote.model.signin.JelloBaseResponse
import com.iskan.remote.model.signin.JelloSignInResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface JelloService {

    @FormUrlEncoded
    @POST("signin.php")
    suspend fun postSignIn(
        @Field("email") email: String,
        @Field("password") password: String
    ) : NetworkResource<JelloBaseResponse<JelloSignInResponse>, ErrorResponse>

    @GET("home.php")
    suspend fun getHome(
        @Header("Authorization") token: String
    ) : NetworkResource<JelloBaseResponse<JelloHomeResponse>, ErrorResponse>

}