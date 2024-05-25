package com.example.data.repository

import com.example.data.model.Category
import com.example.data.model.toCategory
import com.example.network.FoodiesNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultCategoriesRepository @Inject constructor(
    private val dataSource: FoodiesNetworkDataSource
): CategoriesRepository {
    private var _categories = MutableStateFlow<List<Category>>(emptyList())
    private val categories: StateFlow<List<Category>> get() = _categories

    private var isDataLoaded = false
    override suspend fun getCategories(): Flow<List<Category>> {
        ensureDataIsLoaded()
        return categories
    }

    override suspend fun reloadCategories() {
        val newCategories = withContext(Dispatchers.IO) {
            dataSource.getCategories().map { it.toCategory() }
        }
        _categories.update { newCategories }
        isDataLoaded = true
    }

    private suspend fun ensureDataIsLoaded() {
        if (!isDataLoaded) {
            reloadCategories()
        }
    }

}