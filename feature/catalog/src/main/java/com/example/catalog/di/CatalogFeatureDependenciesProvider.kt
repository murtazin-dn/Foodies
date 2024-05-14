package com.example.catalog.di

interface CatalogFeatureDependenciesProvider {

    val deps: CatalogFeatureDependencies

    companion object : CatalogFeatureDependenciesProvider by CatalogFeatureDependenciesStore
}