package com.example.productdetail.di

import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.domain.usecase.ReloadProductsUseCase

interface ProductDetailFeatureDependencies {
    val getProductUseCase: GetProductUseCase
    val addToCartUseCase: AddToCartUseCase
    val reloadProductsUseCase: ReloadProductsUseCase
}