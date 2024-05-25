package com.example.catalog.di

import com.example.catalog.CatalogViewModel
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
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
            removeFromCartUseCase: RemoveFromCartUseCase
        ): CatalogViewModel = CatalogViewModel(
            getCatalogUseCase = getCatalogUseCase,
            addToCartUseCase = addToCartUseCase,
            removeFromCartUseCase = removeFromCartUseCase
        )

}