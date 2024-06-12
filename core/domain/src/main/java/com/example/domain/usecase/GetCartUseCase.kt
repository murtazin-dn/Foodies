package com.example.domain.usecase

import com.example.cart.repository.CartRepository
import com.example.common.Result
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.ProductsRepository
import com.example.domain.mapper.toCategoryModel
import com.example.domain.mapper.toProductModel
import com.example.model.CatalogModel
import com.example.model.CategoryModel
import com.example.model.ProductModel
import com.example.model.ProductsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val cartRepository: CartRepository
) {
    fun execute(): Flow<Result<ProductsModel>> = flow {
        val products = productsRepository.getProducts()
        val cart = cartRepository.cart

        combine(
            cart,
            products
        ) { _cart, _products ->
            val newProducts = when (_products) {
                is Result.Error -> return@combine Result.failure(_products.message)
                is Result.Success -> _products.value.map { product ->
                    product.toProductModel(_cart.find { cartItem ->
                        product.id == cartItem.id
                    }?.count ?: 0)
                }
            }
            val sum = newProducts.sumOf { it.priceCurrent * it.countInCart }
            Result.success(
                ProductsModel(
                    products = newProducts,
                    sum = sum
                )
            )
        }.collect { catalog ->
            emit(catalog)
        }
    }
}