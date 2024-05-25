package com.example.foodies.di

import com.example.catalog.di.CatalogFeatureDependencies
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import com.example.productdetail.di.ProductDetailFeatureDependencies
import dagger.Module
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    fun provideCatalogFeatureDependencies(
        getCatalogUseCase: GetCatalogUseCase,
        addToCartUseCase: AddToCartUseCase,
        removeFromCartUseCase: RemoveFromCartUseCase
    ): CatalogFeatureDependencies{
        return object : CatalogFeatureDependencies {
            override val getCatalogUseCase = getCatalogUseCase

            override val addToCartUseCase = addToCartUseCase

            override val removeFromCartUseCase = removeFromCartUseCase

        }
    }
    @Singleton
    fun provideProductDetailFeatureDependencies(
        getProductUseCase: GetProductUseCase,
        addToCartUseCase: AddToCartUseCase
    ): ProductDetailFeatureDependencies{
        return object : ProductDetailFeatureDependencies {
            override val getProductUseCase = getProductUseCase

            override val addToCartUseCase = addToCartUseCase

        }
    }
}