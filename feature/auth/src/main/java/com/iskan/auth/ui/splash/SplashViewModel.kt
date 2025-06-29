package com.iskan.auth.ui.splash
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SplashUiState {
    data object Loading : SplashUiState()
    data object NavigateToAuth : SplashUiState()
    data object NavigateToHome : SplashUiState()
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    // Inject repository untuk cek login status jika ada
    // private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            // Simulasi delay splash screen
            delay(3000)

            try {
                // TODO: Ganti dengan pengecekan login status sebenarnya
                // val isLoggedIn = authRepository.isUserLoggedIn()
                val isLoggedIn = false // Sementara false

                if (isLoggedIn) {
                    _uiState.value = SplashUiState.NavigateToHome
                } else {
                    _uiState.value = SplashUiState.NavigateToAuth
                }
            } catch (e: Exception) {
                // Jika ada error, arahkan ke auth
                _uiState.value = SplashUiState.NavigateToAuth
            }
        }
    }
}