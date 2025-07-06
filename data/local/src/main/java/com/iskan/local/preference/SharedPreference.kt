package com.iskan.local.preference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Kelas wrapper untuk mengelola SharedPreferences Android
 * Digunakan untuk menyimpan data sederhana seperti token, setting user, dll
 *
 * SharedPreferences adalah cara menyimpan data key-value di Android
 * Data tersimpan permanen hingga aplikasi di-uninstall atau data di-clear
 *
 * @constructor Membuat instance SharedPreference dengan context aplikasi
 * @param context Context yang dibutuhkan untuk membuat SharedPreferences
 */
class SharedPreference @Inject constructor(
    context: Context
) {

    /**
     * Instance SharedPreferences dengan nama file "shared_pref"
     * MODE_PRIVATE berarti file preference hanya bisa diakses oleh aplikasi ini
     */
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)

    /**
     * Editor untuk melakukan perubahan data di SharedPreferences
     * Setiap perubahan harus melalui editor ini
     */
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    /**
     * Menyimpan data String ke SharedPreferences
     *
     * @param key Kunci/identifier untuk data yang disimpan (misal: "token_user", "username")
     * @param value Nilai yang akan disimpan
     *
     * Contoh penggunaan:
     * saveString("username", "JohnDoe")
     * saveString("email", "john@example.com")
     */
    fun saveString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply() // apply() adalah async, lebih cepat dari commit()
    }

    /**
     * Mengambil data String dari SharedPreferences
     *
     * @param key Kunci data yang ingin diambil
     * @return String value yang tersimpan, atau string kosong jika tidak ada
     *
     * Contoh penggunaan:
     * val username = getString("username") // akan return "JohnDoe" jika sudah disimpan
     * val token = getString("token") // akan return "" jika belum pernah disimpan
     */
    fun getString(key: String): String {
        val value = sharedPreferences.getString(key, null)
        return value ?: "" // Jika null, return string kosong
    }
}