package com.iskan.home.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.Surface
import com.iskan.ui.theme.JelloTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JelloTheme {
                Surface() {
                    MainScreen()
                }

            }
        }
    }
}

