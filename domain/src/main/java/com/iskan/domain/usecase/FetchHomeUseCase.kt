package com.iskan.domain.usecase

import com.iskan.domain.mapper.home.JelloHomeUiMapper
import com.iskan.domain.model.base.DomainResource
import com.iskan.domain.model.base.UiState
import com.iskan.domain.model.ui.JelloHomeUiModel
import com.iskan.domain.repository.JelloRepository
import com.iskan.domain.repository.PreferenceRepository
import com.iskan.domain.utils.mapTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use Case untuk fetch data Home
 *
 * Use Case mengenkapsulasi business logic spesifik:
 * - Satu use case = satu business operation
 * - Combine multiple repositories jika perlu
 * - Return data yang siap digunakan UI
 *
 * Flow dalam use case ini:
 * 1. Ambil token dari preference
 * 2. Call API dengan token
 * 3. Transform ke UI model
 * 4. Emit state updates (Loading → Success/Error)
 *
 * @constructor
 * @param repository Repository untuk API calls
 * @param preferenceRepository Repository untuk get saved token
 */
class FetchHomeUseCase @Inject constructor(
    private val repository: JelloRepository,
    private val preferenceRepository: PreferenceRepository
) {

    /**
     * Operator invoke untuk fetch home data
     *
     * @return Flow yang emit UI states
     *
     * Menggunakan Flow untuk:
     * - Emit multiple states (Loading, Success, Error)
     * - Support reactive UI updates
     * - Cancellable operations
     *
     * Steps:
     * 1. Emit Loading state
     * 2. Get saved token
     * 3. Call API dengan "Bearer {token}"
     * 4. Handle response (Success/Error)
     * 5. Map domain model ke UI model
     */
    operator fun invoke() : Flow<UiState<JelloHomeUiModel>> = flow {
        // 1. Emit loading state dulu agar UI show progress
        emit(UiState.OnLoading())

        // 2. Ambil token dari SharedPreferences
        preferenceRepository.getString("token_user").let { savedToken ->
            // 3. Call API dengan Bearer token
            when (val result = repository.fetchHome(token = "Bearer $savedToken")) {
                // 4a. Handle error case
                is DomainResource.OnError -> {
                    emit(UiState.OnFailed(domainResourceError = result.domainResourceError))
                }

                // 4b. Handle success case
                is DomainResource.OnSuccess -> {
                    val dataTemp = result.data
                    // 5. Map domain model ke UI model dan emit
                    emit(UiState.OnSuccess(dataTemp.mapTo(JelloHomeUiMapper())))
                }

                // Ignore other states jika ada
                else -> Unit
            }
        }
    }
}