package com.iskan.home.state

import com.iskan.domain.model.ui.JelloHomeUiModel

/**
 * Sealed class untuk represent semua possible states di Home Screen
 *
 * Sealed class advantages:
 * - Type safety: Compiler tahu semua possible states
 * - Exhaustive when: Harus handle semua cases
 * - No invalid states: Tidak bisa ada state yang tidak terdefinisi
 *
 * Pattern ini memastikan UI selalu dalam state yang valid
 */
sealed class HomeState {

    /**
     * State ketika home data berhasil di-load
     *
     * @property dataUiModel Data home yang siap ditampilkan
     * Nullable untuk handle edge case API return success tapi data null
     */
    data class OnHomeAvailable(
        val dataUiModel : JelloHomeUiModel?
    ) : HomeState()

    /**
     * State ketika sedang loading data
     *
     * Object karena tidak perlu additional data
     * UI akan show loading indicator
     */
    object OnLoading : HomeState()

    /**
     * State ketika terjadi error
     *
     * @property message Error message untuk ditampilkan ke user
     * Bisa dari API error atau network error
     */
    data class OnError(
        val message : String
    ) : HomeState()
}