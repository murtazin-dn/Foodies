package com.example.catalog.di

import kotlin.properties.Delegates.notNull

object CatalogFeatureDependenciesStore: CatalogFeatureDependenciesProvider {
    override var deps: CatalogFeatureDependencies by notNull()
}