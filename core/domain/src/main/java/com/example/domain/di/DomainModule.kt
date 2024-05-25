package com.example.domain.di

import com.example.cart.repository.CartRepository
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.ProductsRepository
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import dagger.Module
import dagger.Provides

@Module
internal class DomainModule {
    @DomainScope
    @Provides
    fun provideGetCatalogUseCase(
        productsRepository: ProductsRepository,
        categoriesRepository: CategoriesRepository,
        cartRepository: CartRepository
    ) = GetCatalogUseCase(
        productsRepository,
        categoriesRepository,
        cartRepository
    )
    @DomainScope
    @Provides
    fun provideGetProductUseCase(
        productsRepository: ProductsRepository,
        cartRepository: CartRepository
    ) = GetProductUseCase(
        productsRepository,
        cartRepository
    )
    @DomainScope
    @Provides
    fun provideAddToCartUseCase(
        cartRepository: CartRepository
    ) = AddToCartUseCase(
        cartRepository
    )
    @DomainScope
    @Provides
    fun provideRemoveFromCartUseCase(
        cartRepository: CartRepository
    ) = RemoveFromCartUseCase(
        cartRepository
    )
}