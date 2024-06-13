package com.example.catalog.di

import com.example.catalog.CatalogViewModel
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.ReloadCategoriesUseCase
import com.example.domain.usecase.ReloadProductsUseCase
import com.example.domain.usecase.ReloadTagsUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import dagger.Module
import dagger.Provides

@Module
internal class CatalogModule {

        @Provides
        @CatalogScope
        fun provideViewModel(
            getCatalogUseCase: GetCatalogUseCase,
            addToCartUseCase: AddToCartUseCase,
            removeFromCartUseCase: RemoveFromCartUseCase,
            reloadTagsUseCase: ReloadTagsUseCase,
            reloadCategoriesUseCase: ReloadCategoriesUseCase,
            reloadProductsUseCase: ReloadProductsUseCase
        ): CatalogViewModel = CatalogViewModel(
            getCatalogUseCase = getCatalogUseCase,
            addToCartUseCase = addToCartUseCase,
            removeFromCartUseCase = removeFromCartUseCase,
            reloadTagsUseCase = reloadTagsUseCase,
            reloadCategoriesUseCase = reloadCategoriesUseCase,
            reloadProductsUseCase = reloadProductsUseCase
        )

}