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
import com.iskan.auth.ui.splash.SplashScreen
import com.iskan.auth.ui.splash.SplashViewModel
import com.iskan.ui.theme.JelloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val signInViewModel: SignInViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()

    sealed class Screen(val route: String) {
        data object Splash : Screen("Splash")
        data object AuthSignIn : Screen("SignIn")
        data object AuthSignUp : Screen("SignUp")
        data object Home : Screen("Home")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JelloTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Splash.route
                ) {
                    composable(Screen.Splash.route) {
                        SplashScreen(
                            viewModel = splashViewModel,
                            onNavigateToAuth = {
                                navController.navigate(Screen.AuthSignIn.route) {
                                    popUpTo(Screen.Splash.route) { inclusive = true }
                                }
                            },
                            onNavigateToHome = {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Splash.route) { inclusive = true }
                                }
                            }
                        )
                    }
                    composable(Screen.AuthSignIn.route){
                        SignInScreen(
                            navController = navController,
                            viewModel = signInViewModel
                            )
                    }
                    composable(Screen.AuthSignUp.route){
                        SignUpScreen(navController = navController)
                    }

                    // Tambahkan composable untuk Home screen jika ada
                    composable(Screen.Home.route) {
                    }
                }

            }
        }
    }
}

