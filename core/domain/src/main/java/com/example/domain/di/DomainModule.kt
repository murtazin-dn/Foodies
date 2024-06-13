package com.example.domain.di

import com.example.cart.repository.CartRepository
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.ProductsRepository
import com.example.data.repository.TagsRepository
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.usecase.ReloadCategoriesUseCase
import com.example.domain.usecase.ReloadProductsUseCase
import com.example.domain.usecase.ReloadTagsUseCase
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
        cartRepository: CartRepository,
        tagsRepository: TagsRepository
    ) = GetCatalogUseCase(
        productsRepository,
        categoriesRepository,
        tagsRepository,
        cartRepository,
    )
    @DomainScope
    @Provides
    fun provideGetProductsUseCase(
        productsRepository: ProductsRepository,
        cartRepository: CartRepository
    ) = GetProductsUseCase(
        productsRepository,
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

    @DomainScope
    @Provides
    fun provideReloadProductsUseCase(
        productsRepository: ProductsRepository
    ) = ReloadProductsUseCase(
        productsRepository
    )
    @DomainScope
    @Provides
    fun provideReloadTagsUseCase(
        tagsRepository: TagsRepository
    ) = ReloadTagsUseCase(
        tagsRepository
    )
    @DomainScope
    @Provides
    fun provideReloadCategoriesUseCase(
        categoriesRepository: CategoriesRepository
    ) = ReloadCategoriesUseCase(
        categoriesRepository
    )
}