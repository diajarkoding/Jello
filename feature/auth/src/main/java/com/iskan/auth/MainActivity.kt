package com.iskan.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iskan.auth.ui.signin.SignInScreen
import com.iskan.auth.ui.signin.SignInViewModel
import com.iskan.auth.ui.signup.SignUpScreen
import com.iskan.ui.theme.JelloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val signInViewModel : SignInViewModel by viewModels()

    sealed class Screen(val route: String) {
        data object AuthSignIn : Screen("SignIn")
        data object AuthSignUp : Screen("SignUp")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JelloTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.AuthSignIn.route
                ) {
                    composable(Screen.AuthSignIn.route){
                        SignInScreen(
                            navController = navController,
                            viewmodel = signInViewModel
                            )
                    }
                    composable(Screen.AuthSignUp.route){
                        SignUpScreen(navController = navController)
                    }
                }

            }
        }
    }
}

