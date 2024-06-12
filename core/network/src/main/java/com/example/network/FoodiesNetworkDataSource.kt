package com.example.network

import com.example.common.network.ApiResponse
import com.example.network.dto.CategoryDto
import com.example.network.dto.ProductDto
import com.example.network.dto.TagDto

interface FoodiesNetworkDataSource {

    suspend fun getTags(): ApiResponse<List<TagDto>>
    suspend fun getCategories(): ApiResponse<List<CategoryDto>>
    suspend fun getProducts(): ApiResponse<List<ProductDto>>
}