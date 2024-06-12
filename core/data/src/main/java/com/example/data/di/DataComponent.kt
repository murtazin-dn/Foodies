package com.example.data.di

import com.example.data.repository.CategoriesRepository
import com.example.data.repository.ProductsRepository
import com.example.data.repository.TagsRepository
import com.example.network.di.NetworkComponent
import dagger.Component
import dagger.Component.Builder
import javax.inject.Singleton

@DataScope
@Component(modules = [DataModule::class], dependencies = [NetworkComponent::class])
interface DataComponent {

    @Component.Builder
    interface Builder{
        fun networkComponent(networkComponent: NetworkComponent): Builder
        fun build(): DataComponent
    }
    val productsRepository: ProductsRepository
    val categoriesRepository: CategoriesRepository
    val tagsRepository: TagsRepository
}