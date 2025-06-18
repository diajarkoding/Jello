package com.iskan.home.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import com.iskan.ui.components.JelloImageViewPhotoUrlRounded
import com.iskan.ui.components.JelloTextRegular
import com.iskan.ui.components.RatingBar
import com.iskan.ui.theme.Gray
import com.iskan.ui.theme.LightGrayishBlue
import com.iskan.ui.theme.VeryLightGray
import com.iskan.ui.theme.VividRed

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

        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = 2.dp,
            color = VeryLightGray

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            JelloTextRegular(
                text = "NEW PRODUCT",
                modifier = Modifier.weight(1f)
            )
            JelloImageViewClick(
                imageVector = Icons.Default.Search,
                color = LightGrayishBlue
            )
            JelloImageViewClick(
                imageVector = Icons.Default.Menu,
                color = LightGrayishBlue
            )
        }

        ItemProduct()

    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductScreenPreview(){
    ProductScreen()
}

@Composable
fun ItemProduct() {
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
            .background(Color.White)
    ) {
        items(10) {
            Row {
                Card(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .size(100.dp)
                        .clickable { /* Handle click */ },
                    colors = CardDefaults.cardColors(
                        containerColor = LightGrayishBlue
                    )
                ) {
                    JelloImageViewPhotoUrlRounded(
                        url = "https://picsum.photos/200/300",
                        description = ""
                    )
                }
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Column {
                    JelloTextRegular(
                        text = "Product Name",
                        modifier = Modifier,
                    )
                    JelloTextRegular(
                        text = "Rp. 100.000",
                        modifier = Modifier.padding(top = 7.dp),
                        color = VividRed
                    )
                    RatingBar(
                        rating = 2f,
                        modifier = Modifier.padding(top = 18.dp),
                        onRatingChanged = { /* Handle rating change */ }
                    )
                }
            }
        }
    }
}