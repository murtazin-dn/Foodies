package com.example.data.repository

import com.example.common.Result
import com.example.common.network.ApiResponse
import com.example.common.network.NetworkError
import com.example.data.model.Product
import com.example.data.model.toCategory
import com.example.data.model.toProduct
import com.example.network.FoodiesNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultProductsRepository @Inject constructor(
    private val dataSource: FoodiesNetworkDataSource
): ProductsRepository {

    private var _products = MutableStateFlow<Result<List<Product>>>(Result.success(emptyList()))
    private val products: StateFlow<Result<List<Product>>> get() = _products

    private var isDataLoaded = false
    override suspend fun getProducts(): Flow<Result<List<Product>>> {
        ensureDataIsLoaded()
        return products
    }

    override suspend fun reloadProducts() {
        val newProducts = withContext(Dispatchers.IO){
            val data = dataSource.getProducts()
            when (data) {
                is ApiResponse.Success -> Result.success(data.value.map { it.toProduct() })
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
        _products.update { newProducts }
        isDataLoaded = true
    }

    private suspend fun ensureDataIsLoaded() {
        if (!isDataLoaded) {
            reloadProducts()
        }
    }

}
