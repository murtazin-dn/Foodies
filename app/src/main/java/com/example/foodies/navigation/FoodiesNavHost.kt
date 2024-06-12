package com.example.foodies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cart.navigation.cartScreen
import com.example.cart.navigation.navigateToCart
import com.example.catalog.navigation.CATALOG_ROUTE
import com.example.catalog.navigation.catalogScreen
import com.example.productdetail.navigation.navigateToProductDetail
import com.example.productdetail.navigation.productDetailScreen

@Composable
fun FoodiesNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CATALOG_ROUTE
    ){
        catalogScreen(
            navigateToProductDetail = navController::navigateToProductDetail,
            navigateToCart = navController::navigateToCart
        )
        productDetailScreen(
            navigateBack = navController::popBackStack
        )
        cartScreen (
            navigateBack = navController::popBackStack
        )
    }
}