package com.iskan.home.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iskan.home.ui.ScreenContent
import com.iskan.ui.components.JelloImageViewClick
import com.iskan.ui.components.JelloTextRegular
import com.iskan.ui.theme.Gray

@Composable
fun ProductScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable(){},
            colors = CardDefaults.cardColors(
                containerColor = Gray.copy(alpha = 0.1f)
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                JelloTextRegular(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    text = "Lihat semua produk",
                    color = Gray
                )

                JelloImageViewClick(
                    color = Gray,
                    onClick = {},
                    imageVector = Icons.Outlined.Search
                )

            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductScreenPreview(){
    ProductScreen()
}