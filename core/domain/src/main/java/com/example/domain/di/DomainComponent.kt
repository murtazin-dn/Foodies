package com.example.domain.di

import com.example.cart.di.CartComponent
import com.example.data.di.DataComponent
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import com.example.network.di.NetworkComponent
import dagger.Component

@DomainScope
@Component(modules = [DomainModule::class], dependencies = [DataComponent::class, CartComponent::class])
interface DomainComponent {
    @Component.Builder
    interface Builder{
        fun dataComponent(dataComponent: DataComponent): Builder
        fun cartComponent(cartComponent: CartComponent): Builder
        fun build(): DomainComponent
    }
    val getCatalogUseCase: GetCatalogUseCase
    val getProductUseCase: GetProductUseCase
    val getProductsUseCase: GetProductsUseCase
    val addToCartUseCase: AddToCartUseCase
    val removeFromCartUseCase: RemoveFromCartUseCase
}