package com.example.productdetail.di

import com.example.productdetail.ProductDetailViewModel
import com.example.productdetail.ProductDetailViewModelFactory
import dagger.Component

@ProductDetailScope
@Component(modules = [ProductDetailModule::class], dependencies = [ProductDetailFeatureDependencies::class])
interface ProductDetailComponent {

    @Component.Builder
    interface Builder {
        fun productDetailFeatureDependencies(productDetailFeatureDependencies: ProductDetailFeatureDependencies): Builder
        fun build(): ProductDetailComponent
    }

    fun getViewModelFactory() : ProductDetailViewModel.Factory
}