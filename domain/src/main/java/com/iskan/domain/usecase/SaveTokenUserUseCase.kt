package com.iskan.domain.usecase

import com.iskan.domain.repository.PreferenceRepository
import javax.inject.Inject

/**
 * Use Case untuk menyimpan token user
 *
 * Berbeda dengan GetTokenUserUseCase, use case ini:
 * - Tidak menggunakan Flow karena operasi save tidak perlu di-observe
 * - Synchronous karena SharedPreferences.apply() sudah async
 *
 * Token biasanya digunakan untuk:
 * - Autentikasi API (Bearer token)
 * - Session management
 * - Remember me functionality
 *
 * @constructor
 * @param repository Repository untuk mengakses preference storage
 */
class SaveTokenUserUseCase @Inject constructor(
    private val repository: PreferenceRepository
) {

    /**
     * Menyimpan token user ke preference storage
     *
     * Contoh penggunaan:
     * saveTokenUserUseCase("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
     *
     * atau:
     * val saveToken = SaveTokenUserUseCase(repository)
     * saveToken(newToken)
     *
     * @param token Token yang akan disimpan (biasanya JWT atau API key)
     * Token disimpan dengan key tetap "token_user" untuk konsistensi
     */
    operator fun invoke(token: String) {
        repository.saveString("token_user", token)
    }
}