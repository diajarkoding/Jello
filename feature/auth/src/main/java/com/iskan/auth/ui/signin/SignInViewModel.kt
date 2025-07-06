package com.iskan.auth.ui.signin

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diajarkoding.navigator.Navigator
import com.iskan.auth.state.SignInState
import com.iskan.domain.model.base.UiState
import com.iskan.domain.usecase.GetTokenUserUseCase
import com.iskan.domain.usecase.PostJelloSignInUseCase
import com.iskan.domain.usecase.SaveTokenUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel untuk halaman Sign In / Login
 *
 * ViewModel adalah bagian dari MVVM Architecture Pattern yang bertugas:
 * - Menyimpan dan mengelola data UI
 * - Bertahan saat configuration changes (rotasi layar, dll)
 * - Menjembatani antara UI (Activity/Fragment) dengan business logic (Use Cases)
 * - Mengelola lifecycle-aware operations
 *
 * @HiltViewModel menandakan ViewModel ini akan di-inject oleh Hilt
 * Hilt akan otomatis menyediakan semua dependency yang dibutuhkan
 *
 * @constructor
 * @param navigator Untuk navigasi antar halaman/feature
 * @param postJelloSignInUseCase Use case untuk melakukan proses login ke server
 * @param saveTokenUserUseCase Use case untuk menyimpan token setelah login berhasil
 * @param getTokenUserUseCase Use case untuk mengambil token yang tersimpan
 */
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val navigator: Navigator,
    private val postJelloSignInUseCase: PostJelloSignInUseCase,
    private val saveTokenUserUseCase: SaveTokenUserUseCase,
    private val getTokenUserUseCase: GetTokenUserUseCase,
) : ViewModel() {

    /**
     * MutableLiveData untuk internal state sign in
     * MutableLiveData = LiveData yang bisa diubah nilainya
     *
     * Private agar hanya bisa diubah dari dalam ViewModel
     * Ini mencegah UI mengubah state secara langsung
     */
    private val _signIn: MutableLiveData<SignInState> = MutableLiveData()

    /**
     * LiveData publik untuk UI observe
     * LiveData = Data holder yang lifecycle-aware
     *
     * UI akan observe LiveData ini untuk mendapatkan update state sign in
     * Contoh state: Loading, Success, Error
     */
    val signIn: LiveData<SignInState> = _signIn

    /**
     * MutableLiveData untuk menyimpan token user
     * Token ini digunakan untuk autentikasi di request selanjutnya
     */
    private val _token: MutableLiveData<String> = MutableLiveData()

    /**
     * LiveData publik untuk UI observe token
     * UI bisa menggunakan token ini untuk berbagai keperluan
     * Misal: menampilkan di debug screen, atau cek apakah user sudah login
     */
    val token: LiveData<String> = _token

    /**
     * Fungsi untuk melakukan proses sign in / login
     *
     * @param email Email user yang akan login
     * @param password Password user yang akan login
     *
     * Alur proses:
     * 1. Launch coroutine di Main thread (untuk update UI)
     * 2. Panggil use case sign in dengan email & password
     * 3. Collect Flow hasil dari use case
     * 4. Update UI state berdasarkan hasil:
     *    - Loading: tampilkan progress indicator
     *    - Success: simpan token & navigasi ke home
     *    - Error: tampilkan pesan error
     *
     * viewModelScope memastikan coroutine otomatis dibatalkan
     * saat ViewModel di-destroy (mencegah memory leak)
     */
    fun postSignIn(email: String, password: String) =
        viewModelScope.launch(Dispatchers.Main) {
            // Collect Flow dari use case
            postJelloSignInUseCase(email, password).collect { uiState ->
                when (uiState) {
                    // State saat request gagal
                    is UiState.OnFailed -> {
                        // Update LiveData dengan state error
                        // orEmpty() untuk menghindari null pointer jika message null
                        _signIn.value = SignInState.OnSignInError(
                            uiState.domainResourceError?.message.orEmpty()
                        )
                    }

                    // State saat request sedang berjalan
                    is UiState.OnLoading -> {
                        // Update LiveData dengan state loading
                        // UI akan menampilkan loading indicator
                        _signIn.value = SignInState.OnSignInLoading
                    }

                    // State saat request berhasil
                    is UiState.OnSuccess -> {
                        // Jika data tidak null, simpan token
                        uiState.data?.let { signInData ->
                            // Simpan token ke SharedPreferences untuk digunakan nanti
                            saveTokenUserUseCase.invoke(signInData.token)
                        }
                        // Update LiveData dengan state success beserta datanya
                        _signIn.value = SignInState.OnSignInAvailable(uiState.data)
                    }
                }
            }
        }

    /**
     * Fungsi untuk navigasi ke halaman Home
     *
     * @param context Context diperlukan untuk melakukan navigasi
     *
     * Dipanggil setelah login berhasil atau saat user sudah memiliki token valid
     * Navigator akan handle perpindahan ke module/feature home
     */
    fun onNavigateToHome(context: Context) {
        navigator.navigateToFeatureHome(context)
    }

    /**
     * Fungsi untuk mengambil token yang tersimpan
     *
     * Digunakan untuk:
     * - Cek apakah user sudah pernah login (ada token tersimpan)
     * - Mendapatkan token untuk keperluan autentikasi API
     * - Auto-login jika token masih valid
     *
     * Flow:
     * 1. Launch coroutine di Main thread
     * 2. Collect token dari use case (yang mengambil dari SharedPreferences)
     * 3. Update LiveData token dengan nilai yang didapat
     *
     * UI yang observe token LiveData akan mendapat update otomatis
     */
    fun getToken() {
        viewModelScope.launch(Dispatchers.Main) {
            getTokenUserUseCase().collect { tokenValue ->
                _token.value = tokenValue
            }
        }
    }
}

/**
 * CARA PENGGUNAAN DI UI (Activity/Fragment):
 *
 * class SignInActivity : AppCompatActivity() {
 *
 *     private val viewModel: SignInViewModel by viewModels()
 *
 *     override fun onCreate() {
 *         // Observe state sign in
 *         viewModel.signIn.observe(this) { state ->
 *             when (state) {
 *                 is SignInState.OnSignInLoading -> showLoading()
 *                 is SignInState.OnSignInError -> showError(state.message)
 *                 is SignInState.OnSignInAvailable -> {
 *                     hideLoading()
 *                     viewModel.onNavigateToHome(this)
 *                 }
 *             }
 *         }
 *
 *         // Observe token
 *         viewModel.token.observe(this) { token ->
 *             if (token.isNotEmpty()) {
 *                 // User sudah login, langsung ke home
 *                 viewModel.onNavigateToHome(this)
 *             }
 *         }
 *
 *         // Cek token saat pertama kali buka
 *         viewModel.getToken()
 *
 *         // Handle button login click
 *         btnLogin.setOnClickListener {
 *             val email = etEmail.text.toString()
 *             val password = etPassword.text.toString()
 *             viewModel.postSignIn(email, password)
 *         }
 *     }
 * }
 *
 * BEST PRACTICES:
 * 1. ViewModel tidak boleh menyimpan reference Context/Activity/View
 * 2. Gunakan LiveData untuk communication dengan UI
 * 3. Business logic ada di Use Case, bukan di ViewModel
 * 4. ViewModel hanya orchestrate/koordinasi antara UI dan Use Case
 * 5. Gunakan sealed class untuk State management (SignInState)
 */