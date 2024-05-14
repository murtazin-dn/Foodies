package com.example.data.repository

import com.example.data.model.Category
import com.example.data.model.toCategory
import com.example.network.FoodiesNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultCategoriesRepository @Inject constructor(
    private val dataSource: FoodiesNetworkDataSource
): CategoriesRepository {
    override suspend fun getCategories(): Flow<List<Category>> = flow {
        emit(dataSource.getCategories().map { it.toCategory() } )
    }
}