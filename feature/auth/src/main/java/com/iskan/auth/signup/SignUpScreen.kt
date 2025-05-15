package com.iskan.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iskan.ui.components.JelloButtonPrimary
import com.iskan.ui.components.JelloButtonSosmedRow
import com.iskan.ui.components.JelloEditText
import com.iskan.ui.components.JelloImageViewClick
import com.iskan.ui.components.JelloTextHeader
import com.iskan.ui.components.JelloTextRegular
import com.iskan.ui.components.JelloTextRegularWithClick
import com.iskan.ui.components.JelloTextViewRow

@Composable
fun SignUpScreen(
    navController: NavController = rememberNavController()
) {
    Column(
        modifier = Modifier.fillMaxSize().
                padding(top = 16.dp).background(color = Color.White)
    ) {
        JelloImageViewClick(onClick = {
            navController.popBackStack()
        })

        JelloTextHeader(
            text = "Create your account"
        )

        JelloTextRegularWithClick(
            text = "Do you already have account ? ",
            textClick = "Sign Up",
            onClick = {
                navController.popBackStack()
            }
        )

        JelloTextRegular(text = "Username")

        JelloEditText(value = "Username")

        JelloTextRegular()

        JelloEditText()

        JelloTextRegular(text = "Password")

        JelloEditText(value = "Password", visualTransformation = PasswordVisualTransformation())

        JelloButtonPrimary(text = "Create Account")

    }
}

@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen()
}