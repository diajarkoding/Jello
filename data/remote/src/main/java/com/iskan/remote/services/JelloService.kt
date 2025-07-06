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

/**
 * Retrofit Service Interface untuk API Jello
 *
 * Interface ini mendefinisikan semua endpoint API yang digunakan aplikasi
 * Retrofit akan generate implementasi dari interface ini
 *
 * Best Practices:
 * - Gunakan suspend function untuk support Coroutines
 * - Return type dibungkus NetworkResource untuk handle success/error
 * - Nama function jelas menggambarkan HTTP method dan tujuan
 */
interface JelloService {

    /**
     * API untuk Sign In / Login
     *
     * @FormUrlEncoded - Data dikirim sebagai form-data (bukan JSON)
     * @POST - HTTP POST method ke endpoint "signin.php"
     *
     * @param email Email user untuk login
     * @param password Password user untuk login
     * @return NetworkResource yang berisi response atau error
     *
     * NetworkResource wrapper untuk handle:
     * - Success response (JelloBaseResponse<JelloSignInResponse>)
     * - Error response (ErrorResponse)
     */
    @FormUrlEncoded
    @POST("signin.php")
    suspend fun postSignIn(
        @Field("email") email: String,
        @Field("password") password: String
    ) : NetworkResource<JelloBaseResponse<JelloSignInResponse>, ErrorResponse>

    /**
     * API untuk fetch data Home
     *
     * @GET - HTTP GET method ke endpoint "home.php"
     * @Header - Mengirim token di header untuk autentikasi
     *
     * @param token Bearer token untuk autentikasi (format: "Bearer xxxxx")
     * @return NetworkResource yang berisi home data atau error
     *
     * Header Authorization standard untuk REST API:
     * - Key: "Authorization"
     * - Value: "Bearer {token}"
     */
    @GET("home.php")
    suspend fun getHome(
        @Header("Authorization") token: String
    ) : NetworkResource<JelloBaseResponse<JelloHomeResponse>, ErrorResponse>
}