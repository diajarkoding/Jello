package com.iskan.home.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iskan.home.ui.ScreenContent
import com.iskan.ui.R
import com.iskan.ui.components.BannerSliderUiJello
import com.iskan.ui.components.JelloImageViewClick
import com.iskan.ui.components.JelloTextRegular
import com.iskan.ui.theme.StrongBlue

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StrongBlue)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable {  },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.2f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    JelloImageViewClick(
                        color = Color.White,
                        imageVector = Icons.Outlined.Search,
                        onClick = {}
                    )

                    JelloTextRegular(
                        text = "Cari barang kamu disini",
                        color = Color.White
                    )
                }
            }

            JelloImageViewClick(
                color = Color.White,
                imageVector = Icons.Outlined.ShoppingCart

            )
        }

        val images = listOf(
            painterResource(id = R.drawable.sample_slide1),
            painterResource(id = R.drawable.sample_slide1),
            painterResource(id = R.drawable.sample_slide1)
        )

        BannerSliderUiJello(
            bannerImage = images,
            onClick = {}
        )
    }
}

@Preview(device = Devices.NEXUS_5, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}