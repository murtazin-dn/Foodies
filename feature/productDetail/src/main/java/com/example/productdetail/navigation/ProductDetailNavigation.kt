package com.example.productdetail.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.common.viewmodel.daggerViewModel
import com.example.productdetail.ProductDetailRoute
import com.example.productdetail.ProductDetailViewModel
import com.example.productdetail.ProductDetailViewModelFactory
import com.example.productdetail.di.DaggerProductDetailComponent
import com.example.productdetail.di.ProductDetailFeatureDependencies
import com.example.productdetail.di.ProductDetailFeatureDependenciesStore

const val PRODUCT_ID = "product_id"
const val PRODUCT_DETAIL_ROUTE_BASE = "product_detail_route"
const val PRODUCT_DETAIL_ROUTE = "$PRODUCT_DETAIL_ROUTE_BASE?$PRODUCT_ID={$PRODUCT_ID}"

fun NavController.navigateToProductDetail(productId: Int, navOptions: NavOptions? = null) {
    val route = PRODUCT_DETAIL_ROUTE.replace("{${PRODUCT_ID}}", productId.toString())
    navigate(route, navOptions)
}

fun NavGraphBuilder.productDetailScreen(
    navigateBack: () -> Unit
) {
    composable(
        route = PRODUCT_DETAIL_ROUTE,
        arguments = listOf(
            navArgument(PRODUCT_ID) { type = NavType.IntType }
        )
    ) {
        val productId = it.arguments?.getInt(PRODUCT_ID) ?: return@composable
        val component = DaggerProductDetailComponent
            .builder()
            .productDetailFeatureDependencies(ProductDetailFeatureDependenciesStore.deps)
            .build()

        val viewModelFactory: ProductDetailViewModel.Factory = component.getViewModelFactory()
        val viewModel: ProductDetailViewModel = viewModel(
            factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return viewModelFactory.create(productId) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }

            }
        )
        ProductDetailRoute(
            viewModel = viewModel,
            onBack = navigateBack
        )
    }
}