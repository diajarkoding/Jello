package com.iskan.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iskan.domain.model.base.UiState
import com.iskan.domain.usecase.FetchHomeUseCase
import com.iskan.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel untuk Home Screen
 *
 * Menggunakan StateFlow instead of LiveData:
 * - StateFlow adalah hot flow (selalu punya value)
 * - Lebih cocok untuk Compose UI
 * - Type-safe dan null-safe
 *
 * @HiltViewModel untuk dependency injection
 * @constructor
 * @param fetchHomeUseCase Use case untuk fetch home data
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchHomeUseCase: FetchHomeUseCase
) : ViewModel() {

    /**
     * Private mutable state flow untuk internal updates
     * Initial state adalah Loading
     */
    private val _home: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.OnLoading)

    /**
     * Public immutable state flow untuk UI observe
     * UI hanya bisa read, tidak bisa modify
     *
     * get() custom getter untuk expose as StateFlow (read-only)
     */
    val home : StateFlow<HomeState> get() = _home

    /**
     * Fungsi untuk fetch home data
     *
     * Dipanggil saat:
     * - Screen pertama kali dibuka
     * - User pull to refresh
     * - Retry after error
     *
     * Flow:
     * 1. Launch coroutine di Main dispatcher
     * 2. Collect flow dari use case
     * 3. Map UiState ke HomeState
     * 4. Update StateFlow value
     */
    fun fetchHome() =
        viewModelScope.launch(Dispatchers.Main) {
            // Collect semua emission dari use case
            fetchHomeUseCase.invoke().collect { uiState ->
                // Transform UiState ke HomeState untuk UI
                when(uiState) {
                    is UiState.OnLoading ->
                        _home.value = HomeState.OnLoading

                    is UiState.OnFailed ->
                        _home.value = HomeState.OnError(
                            uiState.domainResourceError?.message.orEmpty()
                        )

                    is UiState.OnSuccess ->
                        _home.value = HomeState.OnHomeAvailable(uiState.data)
                }
            }
        }
}