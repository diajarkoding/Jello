package com.iskan.jello.di
import com.iskan.domain.repository.JelloRepository
import com.iskan.domain.repository.PreferenceRepository
import com.iskan.remote.repository.JelloRepositoryImpl
import com.iskan.remote.services.JelloService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesJelloRepository(jelloService : JelloService) : JelloRepository {
        return JelloRepositoryImpl(jelloService)
    }

//    @Provides
//    @Singleton
//    fun providesPreferenceRepository(sharedPreference: SharedPreference) : PreferenceRepository {
//        return PreferenceRepositoryImpl(sharedPreference)
//    }
}