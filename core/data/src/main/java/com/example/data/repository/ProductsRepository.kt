package com.example.data.repository

import com.example.common.Result
import com.example.data.model.Product
import com.example.network.dto.CategoryDto
import com.example.network.dto.ProductDto
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<Result<List<Product>>>
    suspend fun reloadProducts()
}