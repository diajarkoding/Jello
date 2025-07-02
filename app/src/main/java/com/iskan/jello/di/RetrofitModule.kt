package com.iskan.jello.di

import com.iskan.jello.annotation.DefaultOkHttpInstance
import com.iskan.jello.annotation.JelloRetrofitInstance
import com.iskan.jello.utils.retrofit.DataTypeAdapterFactory
import com.iskan.jello.utils.retrofit.NetworkResponseAdapterFactory
import com.google.gson.GsonBuilder
import com.iskan.jello.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    @JelloRetrofitInstance
    fun providesRetrofitJello(@DefaultOkHttpInstance okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapterFactory(DataTypeAdapterFactory())
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .setLenient()
                        .create()
                )
            )
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(okHttpClient)
            .build()
    }

}