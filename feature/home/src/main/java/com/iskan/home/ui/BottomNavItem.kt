package com.iskan.home.ui;

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector;

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon:ImageVector
) {
    data object Home: BottomNavItem("home", "Home", Icons.Outlined.Home)
    data object Product: BottomNavItem("product", "Product", Icons.Outlined.ShoppingCart)
    data object Order: BottomNavItem("order", "Order", Icons.AutoMirrored.Outlined.List)
    data object Account: BottomNavItem("account", "Account", Icons.Outlined.Person)
}
