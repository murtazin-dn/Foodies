package com.example.data.repository

import com.example.data.model.Product
import com.example.network.dto.CategoryDto
import com.example.network.dto.ProductDto
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<List<Product>>
    suspend fun reloadProducts()
}