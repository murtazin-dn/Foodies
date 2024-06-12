package com.example.cart.di

import kotlin.properties.Delegates

object CartFeatureDependenciesStore: CartFeatureDependenciesProvider {
    override var deps: CartFeatureDependencies by Delegates.notNull()
}