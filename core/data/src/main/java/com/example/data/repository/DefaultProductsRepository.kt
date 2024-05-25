package com.example.data.repository

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

    private var _products = MutableStateFlow<List<Product>>(emptyList())
    private val products: StateFlow<List<Product>> get() = _products

    private var isDataLoaded = false
    override suspend fun getProducts(): Flow<List<Product>> {
        ensureDataIsLoaded()
        return products
    }

    override suspend fun reloadProducts() {
        val newProducts = withContext(Dispatchers.IO){
            val tagsDiff = async { dataSource.getTags() }
            val productsDiff = async { dataSource.getProducts() }
            val tags = tagsDiff.await()
            productsDiff.await().map {
                it.toProduct(tags)
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
