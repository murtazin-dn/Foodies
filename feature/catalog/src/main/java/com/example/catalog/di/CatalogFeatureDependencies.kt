package com.example.catalog.di

import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.ReloadCategoriesUseCase
import com.example.domain.usecase.ReloadProductsUseCase
import com.example.domain.usecase.ReloadTagsUseCase
import com.example.domain.usecase.RemoveFromCartUseCase

interface CatalogFeatureDependencies {
    val getCatalogUseCase: GetCatalogUseCase
    val addToCartUseCase: AddToCartUseCase
    val removeFromCartUseCase: RemoveFromCartUseCase
    val reloadProductsUseCase: ReloadProductsUseCase
    val reloadTagsUseCase: ReloadTagsUseCase
    val reloadCategoriesUseCase: ReloadCategoriesUseCase

}