package com.iskan.local.di

import android.content.Context
import com.iskan.local.preference.SharedPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Modul Hilt untuk menyediakan dependency terkait Preference/SharedPreferences
 *
 * @Module menandakan ini adalah kelas yang menyediakan dependency untuk Hilt
 * @InstallIn(SingletonComponent::class) berarti dependency yang disediakan akan hidup
 * selama aplikasi berjalan (singleton/single instance)
 */
@Module
@InstallIn(SingletonComponent::class)
class PreferenceModule {

    /**
     * Fungsi untuk menyediakan instance SharedPreference ke seluruh aplikasi
     *
     * @Provides menandakan fungsi ini menyediakan dependency
     * @Singleton memastikan hanya ada 1 instance SharedPreference di aplikasi
     * @param context Context aplikasi yang disediakan otomatis oleh Hilt
     * @return Instance SharedPreference yang siap digunakan
     */
    @Provides
    @Singleton
    fun providePreference(
        @ApplicationContext context: Context
    ): SharedPreference {
        return SharedPreference(context)
    }
}