package com.iskan.local.repository

import com.iskan.domain.repository.PreferenceRepository
import com.iskan.local.preference.SharedPreference
import javax.inject.Inject

/**
 * Implementasi konkret dari PreferenceRepository interface
 *
 * Ini adalah bagian dari Clean Architecture - Data Layer
 * Kelas ini yang benar-benar melakukan penyimpanan/pengambilan data
 * menggunakan SharedPreference Android
 *
 * @constructor
 * @param sharedPreference Instance SharedPreference yang di-inject oleh Hilt
 */
class PreferenceRepositoryImpl @Inject constructor(
    private val sharedPreference: SharedPreference
) : PreferenceRepository {

    /**
     * Implementasi getString - mengambil data dari SharedPreference
     * Fungsi ini hanya meneruskan ke SharedPreference.getString()
     */
    override fun getString(key: String): String =
        sharedPreference.getString(key)

    /**
     * Implementasi saveString - menyimpan data ke SharedPreference
     * Fungsi ini hanya meneruskan ke SharedPreference.saveString()
     */
    override fun saveString(key: String, value: String) {
        sharedPreference.saveString(key, value)
    }
}