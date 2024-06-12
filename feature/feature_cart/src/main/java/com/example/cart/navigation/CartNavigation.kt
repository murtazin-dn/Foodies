package com.example.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cart.CartRoute
import com.example.cart.CartViewModel
import com.example.cart.di.CartFeatureDependenciesStore
import com.example.cart.di.DaggerCartComponent
import com.example.cart.di.DaggerCartFeatureComponent
import com.example.common.viewmodel.daggerViewModel

const val CART_ROUTE = "cart_route"

fun NavController.navigateToCart(navOptions: NavOptions? = null) = navigate(CART_ROUTE, navOptions)

fun NavGraphBuilder.cartScreen(
    navigateBack: () -> Unit
) {
    composable(route = CART_ROUTE) {

        val component = DaggerCartFeatureComponent
            .builder()
            .cartFeatureDependencies(CartFeatureDependenciesStore.deps)
            .build()

        val viewModel: CartViewModel = daggerViewModel {
            component.getViewModel()
        }
        CartRoute(
            viewModel = viewModel,
            onBack = navigateBack
        )
    }
}