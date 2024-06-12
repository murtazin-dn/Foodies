package com.example.data.repository

import com.example.common.Result
import com.example.common.network.ApiResponse
import com.example.common.network.NetworkError
import com.example.data.model.Category
import com.example.data.model.toCategory
import com.example.network.FoodiesNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultCategoriesRepository @Inject constructor(
    private val dataSource: FoodiesNetworkDataSource
) : CategoriesRepository {
    private var _categories = MutableStateFlow<Result<List<Category>>>(Result.success(emptyList()))
    private val categories: StateFlow<Result<List<Category>>> get() = _categories

    private var isDataLoaded = false
    override suspend fun getCategories(): Flow<Result<List<Category>>> {
        ensureDataIsLoaded()
        return categories
    }

    override suspend fun reloadCategories() {
        val newCategories = withContext(Dispatchers.IO) {
            val data = dataSource.getCategories()
            when (data) {
                is ApiResponse.Success -> Result.success(data.value.map { it.toCategory() })
                is ApiResponse.Error -> {
                    Result.failure(
                        when (data.error) {
                            is NetworkError.HttpError -> data.error.message
                            NetworkError.NetworkUnavailable -> data.error.message
                            NetworkError.Timeout -> data.error.message
                            is NetworkError.Unknown -> data.error.message
                        }
                    )
                }
            }
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