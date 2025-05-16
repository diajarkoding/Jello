package com.iskan.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductScreen() {
    ScreenContent("Product")
}

@Composable
fun OrderScreen() {
    ScreenContent("Order")
}

@Composable
fun AccountScreen() {
    ScreenContent("Account")
}

@Composable
fun ScreenContent(title:String) {
    Column {
        Text(text = title)
    }
}