package com.example.productdetail.di

interface ProductDetailFeatureDependenciesProvider {

    val deps: ProductDetailFeatureDependencies

    companion object : ProductDetailFeatureDependenciesProvider by ProductDetailFeatureDependenciesStore
}