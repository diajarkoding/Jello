package com.iskan.auth.ui.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iskan.auth.MainActivity.Screen
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
    viewmodel: SignInViewModel = hiltViewModel(),
){
    Column (modifier = Modifier.fillMaxSize().
        padding(top = 16.dp).
    background(color = Color.White)) {

        val context = LocalContext.current

        JelloImageViewClick(onClick = {})

//        Spacer(modifier = Modifier.height(30.dp))

        JelloTextHeader()

//        Spacer(modifier = Modifier.height(10.dp))

        JelloTextRegularWithClick(
            onClick = {
                navController.navigate(Screen.AuthSignUp.route)
            }
        )

//        Spacer(modifier = Modifier.height(25.dp))

        JelloTextRegular()

//        Spacer(modifier = Modifier.height(10.dp))

        JelloEditText()

//        Spacer(modifier = Modifier.height(25.dp))

        JelloTextRegular(text = "Password")

//        Spacer(modifier = Modifier.height(10.dp))

        JelloEditText(value = "Password",   visualTransformation = PasswordVisualTransformation())

        JelloTextViewRow()

        JelloButtonPrimary(
            onClick = {
                viewmodel.onNavigateToHome(context)
            },
        )

        JelloButtonSosmedRow()

    }
}

@Preview(device = Devices.NEXUS_5, showSystemUi = true)
@Composable
fun SignInScreenPreview(){
    SignInScreen()
}