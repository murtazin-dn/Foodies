package com.example.foodies.di

import com.example.cart.di.CartFeatureDependencies
import com.example.catalog.di.CatalogFeatureDependencies
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.usecase.ReloadCategoriesUseCase
import com.example.domain.usecase.ReloadProductsUseCase
import com.example.domain.usecase.ReloadTagsUseCase
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
        removeFromCartUseCase: RemoveFromCartUseCase,
        reloadProductsUseCase: ReloadProductsUseCase,
        reloadTagsUseCase: ReloadTagsUseCase,
        reloadCategoriesUseCase: ReloadCategoriesUseCase
    ): CatalogFeatureDependencies{
        return object : CatalogFeatureDependencies {
            override val getCatalogUseCase = getCatalogUseCase

            override val addToCartUseCase = addToCartUseCase

            override val removeFromCartUseCase = removeFromCartUseCase

            override val reloadProductsUseCase =  reloadProductsUseCase

            override val reloadTagsUseCase = reloadTagsUseCase

            override val reloadCategoriesUseCase = reloadCategoriesUseCase

        }
    }
    @Singleton
    fun provideCartFeatureDependencies(
        getProductsUseCase: GetProductsUseCase,
        addToCartUseCase: AddToCartUseCase,
        removeFromCartUseCase: RemoveFromCartUseCase,
        reloadProductsUseCase: ReloadProductsUseCase
    ): CartFeatureDependencies {
        return object : CartFeatureDependencies {
            override val getProductsUseCase = getProductsUseCase

            override val addToCartUseCase = addToCartUseCase

            override val removeFromCartUseCase = removeFromCartUseCase

            override val reloadProductsUseCase = reloadProductsUseCase

        }
    }
    @Singleton
    fun provideProductDetailFeatureDependencies(
        getProductUseCase: GetProductUseCase,
        addToCartUseCase: AddToCartUseCase,
        reloadProductsUseCase: ReloadProductsUseCase
    ): ProductDetailFeatureDependencies{
        return object : ProductDetailFeatureDependencies {
            override val getProductUseCase = getProductUseCase

            override val addToCartUseCase = addToCartUseCase

            override val reloadProductsUseCase = reloadProductsUseCase

        }
    }
}