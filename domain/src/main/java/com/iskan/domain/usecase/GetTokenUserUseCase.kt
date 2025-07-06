package com.iskan.domain.usecase

import com.iskan.domain.repository.PreferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use Case untuk mengambil token user yang tersimpan
 *
 * Use Case adalah bagian dari Clean Architecture yang berisi business logic
 * Setiap Use Case hanya melakukan SATU hal spesifik (Single Responsibility)
 *
 * Menggunakan Flow untuk mendukung reactive programming:
 * - Data bisa di-observe perubahannya
 * - Mendukung operasi asynchronous
 * - Bisa di-cancel jika tidak dibutuhkan
 *
 * @constructor
 * @param repository Repository untuk mengakses preference storage
 */
class GetTokenUserUseCase @Inject constructor(
    private val repository: PreferenceRepository
) {

    /**
     * Fungsi operator invoke membuat class ini bisa dipanggil seperti fungsi
     *
     * Contoh penggunaan:
     * val getToken = GetTokenUserUseCase(repository)
     * getToken().collect { token ->
     *     // gunakan token
     * }
     *
     * atau bisa juga:
     * getTokenUserUseCase().collect { ... }
     *
     * @return Flow<String> yang emit token user
     * Flow dijalankan di IO thread untuk menghindari blocking UI
     */
    operator fun invoke(): Flow<String> = flow {
        // Emit token yang tersimpan dengan key "token_user"
        emit(repository.getString("token_user"))
    }.flowOn(Dispatchers.IO) // Pindahkan eksekusi ke IO thread
}