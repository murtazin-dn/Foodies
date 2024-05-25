package com.example.productdetail.di

import kotlin.properties.Delegates

object ProductDetailFeatureDependenciesStore : ProductDetailFeatureDependenciesProvider {
    override var deps: ProductDetailFeatureDependencies by Delegates.notNull()
}