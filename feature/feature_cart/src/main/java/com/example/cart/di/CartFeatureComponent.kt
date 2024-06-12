package com.example.cart.di

import com.example.cart.CartViewModel
import dagger.Component

@Component(
    modules = [CartFeatureModule::class],
    dependencies = [CartFeatureDependencies::class]
)
@CartFeatureScope
internal interface CartFeatureComponent {
    @Component.Builder
    interface Builder {
        fun cartFeatureDependencies(cartFeatureDependencies: CartFeatureDependencies): Builder
        fun build(): CartFeatureComponent
    }

    fun getViewModel() : CartViewModel
}