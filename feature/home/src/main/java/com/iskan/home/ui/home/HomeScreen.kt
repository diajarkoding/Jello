package com.iskan.home.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iskan.home.ui.ScreenContent
import com.iskan.ui.R
import com.iskan.ui.components.BannerSliderUiJello
import com.iskan.ui.components.JelloImageViewClick
import com.iskan.ui.components.JelloImageViewPhotoUrlRounded
import com.iskan.ui.components.JelloTextRegular
import com.iskan.ui.theme.LightGrayishBlue
import com.iskan.ui.theme.StrongBlue
import com.iskan.ui.theme.VividMagenta

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

        Spacer(modifier = Modifier.weight(1f))

        val temp = listOf(
            ProductItem(
                title = "CATEGORIES",
                subItem = listOf(
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                )
            ),
            ProductItem(
                title = "NEW PRODUCT",
                subItem = listOf(
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                )
            ),
        )

        ItemProductHomeList(temp)
    }
}

@Preview(device = Devices.NEXUS_5, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}

data class ProductItem(
    val title: String,
    val subItem: List<String>
)

@Composable
fun ItemProductHomeList(items: List<ProductItem>){
    LazyColumn(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color.White)
    ) {
        items(items) {item ->
            Row(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                JelloTextRegular(
                    text = item.title,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(16.dp)
                )

                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(color = VividMagenta)
                    ) {
                        append("SEE ALL")
                    }
                }

                ClickableText(text = annotatedString, onClick = {})

            }

            if (item.subItem.isNotEmpty()){
                SubItemList(subItem = item.subItem)
            }
        }
    }

}

@Preview
@Composable
fun ItemProductHomeListPreview(){
    val temp = listOf(
        ProductItem(
            title = "CATEGORIES",
            subItem = listOf(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            )
        ),
        ProductItem(
            title = "NEW PRODUCT",
            subItem = listOf(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            )
        ),
    )

    ItemProductHomeList(temp)
}

@Composable
fun SubItemList(subItem: List<String>){
    LazyRow(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        items(subItem) { itemUrl ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, bottom = 16.dp)
                    .clickable {},
                colors = CardDefaults.cardColors(
                    containerColor = LightGrayishBlue
                )
            ) {
                JelloImageViewPhotoUrlRounded(
                    url = itemUrl,
                    description = "Image ke $itemUrl"
                )
            }
        }
    }
}

@Preview
@Composable
fun SubItemListPreview() {
    val dummyImageUrls = listOf(
        "https://picsum.photos/id/237/200/300",
        "https://picsum.photos/id/238/200/300",
        "https://picsum.photos/id/239/200/300"
    )

    SubItemList(dummyImageUrls)
}