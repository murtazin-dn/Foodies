package com.example.data.repository

import com.example.common.Result
import com.example.data.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(): Flow<Result<List<Category>>>
    suspend fun reloadCategories()
}