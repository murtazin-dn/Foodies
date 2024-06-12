package com.example.foodies.di

import com.example.cart.di.CartComponent
import com.example.cart.di.CartFeatureDependencies
import com.example.catalog.di.CatalogFeatureDependencies
import com.example.data.di.DataComponent
import com.example.domain.di.DomainComponent
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import com.example.network.di.NetworkComponent
import com.example.productdetail.di.ProductDetailFeatureDependencies
import dagger.BindsInstance
import dagger.Component
import dagger.Component.Builder
import javax.inject.Singleton

@Component(modules = [AppModule::class], dependencies = [DomainComponent::class])
@AppScope
interface AppComponent
    : CatalogFeatureDependencies, ProductDetailFeatureDependencies, CartFeatureDependencies
{
    override val getProductsUseCase: GetProductsUseCase
    override val getProductUseCase: GetProductUseCase
    override val getCatalogUseCase: GetCatalogUseCase
    override val addToCartUseCase: AddToCartUseCase
    override val removeFromCartUseCase: RemoveFromCartUseCase

    @Component.Builder
    interface Builder{

        fun domainComponent(domainComponent: DomainComponent): Builder
        fun build(): AppComponent
    }

}