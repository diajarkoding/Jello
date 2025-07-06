package com.iskan.domain.repository

/**
 * Interface/kontrak untuk repository yang mengelola preference
 *
 * Ini adalah bagian dari Clean Architecture - Domain Layer
 * Domain layer tidak boleh tahu detail implementasi (SharedPreferences, Database, dll)
 * Hanya mendefinisikan apa yang bisa dilakukan, bukan bagaimana melakukannya
 *
 * Keuntungan menggunakan interface:
 * 1. Mudah untuk testing (bisa di-mock)
 * 2. Implementasi bisa diganti tanpa mengubah business logic
 * 3. Memisahkan business logic dari framework Android
 */
interface PreferenceRepository {

    /**
     * Mengambil data String berdasarkan key
     * @param key Identifier data yang ingin diambil
     * @return String value yang tersimpan
     */
    fun getString(key: String) : String

    /**
     * Menyimpan data String dengan key tertentu
     * @param key Identifier untuk data
     * @param value Nilai yang akan disimpan
     */
    fun saveString(key: String, value:String)
}