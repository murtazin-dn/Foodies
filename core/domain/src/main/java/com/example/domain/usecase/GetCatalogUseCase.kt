package com.example.domain.usecase

import com.example.cart.repository.CartRepository
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.ProductsRepository
import com.example.domain.mapper.toCategoryModel
import com.example.model.CategoryModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetCatalogUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val categoriesRepository: CategoriesRepository,
    private val cartRepository: CartRepository
) {
    fun execute(): Flow<List<CategoryModel>> = flow {
        val categories = categoriesRepository.getCategories()
        val products = productsRepository.getProducts()
        val cart = cartRepository.cart

        combine(
            cart,
            categories,
            products
        ){ _cart, _categories, _products ->
            _categories.map {
                it.toCategoryModel(_products, _cart)
            }
        }.collect{ catalog ->
            emit(catalog)
        }
    }
}