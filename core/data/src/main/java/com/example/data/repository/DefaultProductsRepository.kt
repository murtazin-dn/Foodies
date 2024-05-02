package com.example.data.repository

import com.example.data.model.Product
import com.example.data.model.toProduct
import com.example.network.FoodiesNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultProductsRepository @Inject constructor(
    private val dataSource: FoodiesNetworkDataSource
): ProductsRepository {
    override suspend fun getProducts(): Flow<List<Product>> = flow{
        withContext(Dispatchers.IO){
            val tagsDiff = async { dataSource.getTags() }
            val productsDiff = async { dataSource.getProducts() }
            val tags = tagsDiff.await()
            val products = productsDiff.await().map {
                it.toProduct(tags)
            }
            emit(products)
        }
    }
}