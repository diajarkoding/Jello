package com.iskan.home.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iskan.home.ui.account.AccountScreen
import com.iskan.home.ui.home.HomeScreen
import com.iskan.home.ui.order.OrderScreen
import com.iskan.home.ui.product.ProductScreen
import com.iskan.ui.theme.VeryDarkGrayishBlue

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Product,
        BottomNavItem.Order,
        BottomNavItem.Account
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
                item -> BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription =  item.title,
                            tint = VeryDarkGrayishBlue
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 12.sp,
                                textAlign = TextAlign.Center
                            ),
                            color = VeryDarkGrayishBlue
                        )
                    },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route){
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    alwaysShowLabel = true
                )
            }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier){
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = modifier
        ) {
            composable(BottomNavItem.Home.route){ HomeScreen() }
            composable(BottomNavItem.Product.route){ ProductScreen() }
            composable(BottomNavItem.Order.route){ OrderScreen() }
            composable(BottomNavItem.Account.route){ AccountScreen() }
        }
}

@Preview
@Composable
fun MainScreenPreview(){
    MainScreen()
}