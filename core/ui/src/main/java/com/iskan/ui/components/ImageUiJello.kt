package com.iskan.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun JelloImageViewClick(){
    IconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            )
    }
}

@Preview
@Composable
fun JelloImageViewClickPreview(){
    JelloImageViewClick()
}


