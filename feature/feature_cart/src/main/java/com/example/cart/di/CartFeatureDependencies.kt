package com.example.cart.di

import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.usecase.RemoveFromCartUseCase

interface CartFeatureDependencies {
    val getProductsUseCase: GetProductsUseCase
    val addToCartUseCase: AddToCartUseCase
    val removeFromCartUseCase: RemoveFromCartUseCase
}