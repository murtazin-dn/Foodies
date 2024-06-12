package com.example.cart.di

interface CartFeatureDependenciesProvider {

    val deps: CartFeatureDependencies

    companion object : CartFeatureDependenciesProvider by CartFeatureDependenciesStore
}