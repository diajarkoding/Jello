package com.iskan.home.ui.account

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iskan.ui.R
import com.iskan.ui.components.JelloImageViewClick
import com.iskan.ui.components.JelloImageViewPhotoUrlRounded
import com.iskan.ui.components.JelloTextRegular
import com.iskan.ui.theme.StrongBlue

@Composable
fun AccountScreen(title: String = "Account") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = StrongBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            JelloImageViewClick(
                onClick = { /* Handle click */ },
                color = Color.White
            )
            JelloTextRegular(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .weight(1f),
                text = "Profil",
                color = Color.White
            )
            JelloImageViewClick(
                onClick = { /* Handle click */ },
                color = Color.White,
                imageVector = ImageVector.vectorResource(R.drawable.ic_logout)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        JelloImageViewPhotoUrlRounded(
            url = "https://picsum.photos/200/300",
            description = "photo profil"
        )

        Spacer(modifier = Modifier.height(15.dp))

        JelloTextRegular(
            text = "Welcome Nama User",
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(64.dp))
        Spacer(modifier = Modifier.weight(1f))

        val sampleMenu = listOf(
            MenuItem(iconLeft = Icons.Default.Person, label = "Edit Profile", iconRight = Icons.AutoMirrored.Filled.KeyboardArrowRight,),
            MenuItem(iconLeft = Icons.Default.Person, label = "Shipping Address", iconRight = Icons.AutoMirrored.Filled.KeyboardArrowRight,),
            MenuItem(iconLeft = Icons.Default.Warning, label = "Wishlist", iconRight = Icons.AutoMirrored.Filled.KeyboardArrowRight,),
            MenuItem(iconLeft = Icons.Default.FavoriteBorder, label = "Order History", iconRight = Icons.AutoMirrored.Filled.KeyboardArrowRight,),
            MenuItem(iconLeft = Icons.Default.Notifications, label = "Notification", iconRight = Icons.AutoMirrored.Filled.KeyboardArrowRight,),
            MenuItem(iconLeft = Icons.Default.ShoppingCart, label = "Cards", iconRight = Icons.AutoMirrored.Filled.KeyboardArrowRight,),
        )

        LazyColumn(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
        ) {
            items (sampleMenu) { item ->
                ItemMenuAccount(
                    iconLeft = item.iconLeft,
                    label = item.label,
                    iconRight = item.iconRight
                )
            }
        }
    }
}


data class MenuItem(
    val iconLeft: ImageVector,
    val label: String,
    val iconRight: ImageVector
)

@Composable
fun ItemMenuAccount(
    modifier: Modifier = Modifier,
    label: String = "Edit Profile",
    iconLeft: ImageVector = Icons.Default.Person,
    iconRight: ImageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
    colorLeft: Color = Color.Black,
    colorRight: Color = Color.Black
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            JelloImageViewClick(
                color = colorLeft,
                imageVector = iconLeft,
                imageDescription = "Icon Left"
            )
            JelloTextRegular(
                text = label,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
            JelloImageViewClick(
                color = colorRight,
                imageVector = iconRight,
                imageDescription = "Icon Right"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AccountScreenPreview(){
    AccountScreen()
}