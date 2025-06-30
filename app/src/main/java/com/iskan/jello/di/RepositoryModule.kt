package com.iskan.jello.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesJelloRepository(jelloService: JelloService): JelloRepository {
        return JelloRepository(jelloService)
    }
}