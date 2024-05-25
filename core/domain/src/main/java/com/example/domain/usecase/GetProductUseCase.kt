package com.example.domain.usecase

import com.example.cart.repository.CartRepository
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.ProductsRepository
import com.example.domain.mapper.toCategoryModel
import com.example.domain.mapper.toProductModel
import com.example.model.CatalogModel
import com.example.model.CategoryModel
import com.example.model.ProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val cartRepository: CartRepository
) {
    fun execute(id: Int): Flow<ProductModel?> = flow {
        val products = productsRepository.getProducts()
        val cart = cartRepository.cart

        combine(
            cart,
            products
        ) { _cart, _products ->
            _products.find { it.id == id }
                ?.toProductModel(_cart.find {it.id == id }?.count ?: 0)
            }.collect {
            emit(it)
        }
    }
}