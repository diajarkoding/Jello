package com.iskan.auth.state

import com.iskan.domain.model.ui.JelloSignInUiModel

sealed class SignInState {

    data class OnSignInAvailable(
        val signInUiModel: JelloSignInUiModel?
    ) : SignInState()

    object OnSignInLoading : SignInState()

    data class OnSignInError(
        val message: String
    ) : SignInState()



}