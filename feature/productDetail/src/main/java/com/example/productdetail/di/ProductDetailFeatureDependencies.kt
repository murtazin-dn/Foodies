package com.example.productdetail.di

import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductUseCase

interface ProductDetailFeatureDependencies {
    val getProductUseCase: GetProductUseCase
    val addToCartUseCase: AddToCartUseCase
}