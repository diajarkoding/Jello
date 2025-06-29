package com.iskan.home.ui.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iskan.home.ui.product.ItemProduct
import com.iskan.ui.components.JelloImageViewClick
import com.iskan.ui.components.JelloImageViewPhotoUrlRounded
import com.iskan.ui.components.JelloTextRegular
import com.iskan.ui.theme.Gray
import com.iskan.ui.theme.LightGrayishBlue
import com.iskan.ui.theme.VeryLightGray
import com.iskan.ui.theme.VividRed

@Composable
fun OrderScreen() {
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

        HorizontalDivider(
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
                imageVector = ImageVector.vectorResource(com.iskan.ui.R.drawable.ic_filter),
                color = LightGrayishBlue
            )
            JelloImageViewClick(
                imageVector = ImageVector.vectorResource(com.iskan.ui.R.drawable.ic_katalog_more),
                color = LightGrayishBlue
            )
        }

        ItemProductGrid()

    }
}

@Composable
fun ItemProductGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(Color.White),
         contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        items(count = 10) {
            Column {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = LightGrayishBlue
                    )
                ) {
                    JelloImageViewPhotoUrlRounded(
                        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/200.png",
                        description = "Pokemon"
                    )
                }

                JelloTextRegular(
                    text = "Nama Produk",
                    modifier = Modifier.padding(top = 11.dp)
                )
                JelloTextRegular(
                    text = "$ 130",
                    modifier = Modifier.padding(top = 9.dp),
                    color = VividRed
                )
            }
        }

    }
        }



@Preview(showBackground = true)
@Composable
fun OrderScreenPreview(){
    OrderScreen()
}