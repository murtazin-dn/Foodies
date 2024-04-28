package com.example.foodies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.catalog.navigation.CATALOG_ROUTE
import com.example.catalog.navigation.CatalogScreen

@Composable
fun FoodiesNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CATALOG_ROUTE
    ){
        CatalogScreen()
    }
}