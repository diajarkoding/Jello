package com.iskan.jello.di

import com.iskan.jello.annotation.JelloRetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import com.iskan.jello.annotation.DefaultOkHttpInstance
import com.iskan.jello.utils.retrofit.DataTypeAdapterFactory
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    @JelloRetrofitInstance
    fun providesRetrofitJello(@DefaultOkHttpInstance okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapterFactory(DataTypeAdapterFactory())
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'")
                        .create()
                )
            ).addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(okHttpClient)
            .build()
    }
}