package com.example.network

import com.example.network.dto.CategoryDto
import com.example.network.dto.ProductDto
import com.example.network.dto.TagDto

interface FoodiesNetworkDataSource {

    suspend fun getTags(): List<TagDto>
    suspend fun getCategories(): List<CategoryDto>
    suspend fun getProducts(): List<ProductDto>
}