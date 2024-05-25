package com.example.foodies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
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
            navigateToProductDetail = navController::navigateToProductDetail
        )
        productDetailScreen(
            navigateBack = navController::popBackStack
        )
    }
}