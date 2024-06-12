package com.example.data.di

import com.example.data.repository.CategoriesRepository
import com.example.data.repository.DefaultCategoriesRepository
import com.example.data.repository.DefaultProductsRepository
import com.example.data.repository.DefaultTagsRepository
import com.example.data.repository.ProductsRepository
import com.example.data.repository.TagsRepository
import com.example.network.FoodiesNetworkDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DataModule {

    @Provides
    @DataScope
    fun provideProductRepository(
        dataSource: FoodiesNetworkDataSource
    ): ProductsRepository{
        return DefaultProductsRepository(
            dataSource = dataSource
        )
    }
    @Provides
    @DataScope
    fun provideCategoriesRepository(
        dataSource: FoodiesNetworkDataSource
    ): CategoriesRepository {
        return DefaultCategoriesRepository(
            dataSource = dataSource
        )
    }
    @Provides
    @DataScope
    fun provideTagsRepository(
        dataSource: FoodiesNetworkDataSource
    ): TagsRepository {
        return DefaultTagsRepository(
            dataSource = dataSource
        )
    }
}