package com.iskan.auth.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iskan.ui.components.JelloButtonPrimary
import com.iskan.ui.components.JelloButtonSosmedRow
import com.iskan.ui.components.JelloEditText
import com.iskan.ui.components.JelloImageViewClick
import com.iskan.ui.components.JelloTextHeader
import com.iskan.ui.components.JelloTextRegular
import com.iskan.ui.components.JelloTextRegularWithClick
import com.iskan.ui.components.JelloTextViewRow

@Composable
fun SignInScreen(){
    Column (modifier = Modifier.fillMaxSize().
        padding(30.dp).
    background(color = Color.White)) {
        JelloImageViewClick(onClick = {
        })

//        Spacer(modifier = Modifier.height(30.dp))

        JelloTextHeader()

//        Spacer(modifier = Modifier.height(10.dp))

        JelloTextRegularWithClick()

//        Spacer(modifier = Modifier.height(25.dp))

        JelloTextRegular()

//        Spacer(modifier = Modifier.height(10.dp))

        JelloEditText()

//        Spacer(modifier = Modifier.height(25.dp))

        JelloTextRegular(text = "Password")

//        Spacer(modifier = Modifier.height(10.dp))

        JelloEditText(value = "Password",   visualTransformation = PasswordVisualTransformation())

        JelloTextViewRow()

        JelloButtonPrimary()

        JelloButtonSosmedRow()

    }
}

@Preview(device = Devices.NEXUS_5, showSystemUi = true)
@Composable
fun SignInScreenPreview(){
    SignInScreen()
}