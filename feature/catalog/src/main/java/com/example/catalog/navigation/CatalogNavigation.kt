package com.example.catalog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.catalog.CatalogRoute
import com.example.catalog.CatalogViewModel
import com.example.catalog.di.CatalogComponent
import com.example.catalog.di.CatalogFeatureDependenciesProvider
import com.example.catalog.di.DaggerCatalogComponent
import com.example.common.viewmodel.daggerViewModel

const val CATALOG_ROUTE = "catalog_route"

fun NavController.navigateToCatalog(navOptions: NavOptions) = navigate(CATALOG_ROUTE, navOptions)

fun NavGraphBuilder.catalogScreen() {
    composable(route = CATALOG_ROUTE) {
        val component = DaggerCatalogComponent
            .builder()
            .catalogFeatureDependencies(CatalogFeatureDependenciesProvider.deps)
            .build()

        val viewModel: CatalogViewModel = daggerViewModel {
            component.getViewModel()
        }
        CatalogRoute(viewModel)
    }
}