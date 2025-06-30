package com.iskan.jello.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.iskan.jello.annotation.JelloRetrofitInstance
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    fun providesJelloService(
        @JelloRetrofitInstance retrofit: Retrofit
    ): JelloService {
        return retrofit.create(JelloService::class.java)
    }
}