package com.example.cart.di

import com.example.cart.CartViewModel
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.usecase.ReloadProductsUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import dagger.Module
import dagger.Provides

@Module
internal class CartFeatureModule {
    @Provides
    @CartFeatureScope
    fun provideViewModel(
        getProductsUseCase: GetProductsUseCase,
        addToCartUseCase: AddToCartUseCase,
        removeFromCartUseCase: RemoveFromCartUseCase,
        reloadProductsUseCase: ReloadProductsUseCase
    ): CartViewModel = CartViewModel(
        getProductsUseCase = getProductsUseCase,
        addToCartUseCase = addToCartUseCase,
        removeFromCartUseCase = removeFromCartUseCase,
        reloadProductsUseCase = reloadProductsUseCase
    )
}