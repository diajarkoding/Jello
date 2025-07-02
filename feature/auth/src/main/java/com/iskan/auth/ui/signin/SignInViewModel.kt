package com.iskan.auth.ui.signin
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diajarkoding.navigator.Navigator
import com.iskan.auth.state.SignInState
import com.iskan.domain.model.base.UiState
import com.iskan.domain.usecase.PostJelloSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val navigator: Navigator,
    private val postJelloSignInUseCase: PostJelloSignInUseCase,
) : ViewModel() {

    private val _signIn: MutableLiveData<SignInState> = MutableLiveData()
    val signIn: LiveData<SignInState> = _signIn

    fun postSignIn(email: String, password: String) =
        viewModelScope.launch(Dispatchers.Main) {
            postJelloSignInUseCase(email, password).collect{
                when (it) {
                    is UiState.OnFailed -> {
                        _signIn.value = SignInState.OnSignInError(it.domainResourceError?.message.orEmpty())
                    }
                    is UiState.OnLoading -> {
                        _signIn.value = SignInState.OnSignInLoading
                    }
                    is UiState.OnSuccess -> {
//                        it.data?.let {
//                            saveTokenUserUseCase.invoke(it.token)
//                        }
                        _signIn.value = SignInState.OnSignInAvailable(it.data)
                    }
                }
            }
        }

    fun onNavigateToHome(context: Context) {
        navigator.navigateToFeatureHome(context)
    }
}