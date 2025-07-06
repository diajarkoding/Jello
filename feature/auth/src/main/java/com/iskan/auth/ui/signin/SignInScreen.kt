package com.iskan.auth.ui.signin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iskan.auth.MainActivity.Screen
import com.iskan.auth.state.SignInState
import com.iskan.ui.components.JelloButtonPrimary
import com.iskan.ui.components.JelloButtonSosmedRow
import com.iskan.ui.components.JelloEditText
import com.iskan.ui.components.JelloImageViewClick
import com.iskan.ui.components.JelloTextHeader
import com.iskan.ui.components.JelloTextRegular
import com.iskan.ui.components.JelloTextRegularWithClick
import com.iskan.ui.components.JelloTextViewRow

@Composable
fun SignInScreen(
    navController: NavController = rememberNavController(),
    viewModel: SignInViewModel = hiltViewModel(),
){
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val context = LocalContext.current

    viewModel.getToken()
    val token by viewModel.token.observeAsState()
    if (!token.isNullOrEmpty()) {
        viewModel.onNavigateToHome(context)
    }

    val signInState by viewModel.signIn.observeAsState()
    LaunchedEffect(signInState) {
        when (val state = signInState) {
            is SignInState.OnSignInLoading -> {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            }

            is SignInState.OnSignInError -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            is SignInState.OnSignInAvailable -> {
                if (state.signInUiModel?.code == 200) {
                    viewModel.onNavigateToHome(context)
                } else {
                    Toast.makeText(
                        context,
                        "Error ${state.signInUiModel?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            else -> {

            }
        }
    }

    Column (modifier = Modifier.fillMaxSize().
        padding(top = 16.dp).
    background(color = Color.White)) {



        JelloImageViewClick(onClick = {})


        JelloTextHeader()


        JelloTextRegularWithClick(
            onClick = {
                navController.navigate(Screen.AuthSignUp.route)
            }
        )


        JelloTextRegular()


        JelloEditText(
            value = email.value,
            onTyping = {
                email.value = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            errorMessages = "Email is required"
        )


        JelloTextRegular(text = "Password")


        JelloEditText(
            value = password.value,
            onTyping = {
                password.value = it
            },
            visualTransformation = PasswordVisualTransformation(),
            errorMessages = "Password is required"
        )

        JelloTextViewRow()

        JelloButtonPrimary(
            onClick = {
                if (email.value.isBlank() && password.value.isBlank()) {
                    Toast.makeText(context, "Email and Password is required", Toast.LENGTH_SHORT).show()
                    return@JelloButtonPrimary
                } else {
                    viewModel.postSignIn(email = email.value, password = password.value)

                }
            },
            enabled = email.value.isNotBlank() && password.value.isNotBlank(),
        )

        JelloButtonSosmedRow()

    }
}

@Preview(device = Devices.NEXUS_5, showSystemUi = true)
@Composable
fun SignInScreenPreview(){
    SignInScreen()
}