package com.example.catalog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.catalog.CatalogRoute

const val CATALOG_ROUTE = "catalog_route"

fun NavController.navigateToCatalog(navOptions: NavOptions) = navigate(CATALOG_ROUTE, navOptions)

fun NavGraphBuilder.CatalogScreen() {
    composable(route = CATALOG_ROUTE) {
        CatalogRoute()
    }
}