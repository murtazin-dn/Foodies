package com.example.domain.usecase

import com.example.cart.repository.CartRepository
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.ProductsRepository
import com.example.domain.mapper.toCategoryModel
import com.example.domain.mapper.toProductModel
import com.example.model.CatalogModel
import com.example.model.CategoryModel
import com.example.model.ProductModel
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
    fun execute(): Flow<CatalogModel> = flow {
        val categories = categoriesRepository.getCategories()
        val products = productsRepository.getProducts()
        val cart = cartRepository.cart

        combine(
            cart,
            categories,
            products
        ) { _cart, _categories, _products ->
            val newProducts = _products.map { product ->
                product.toProductModel(_cart.find { cartItem ->
                    product.id == cartItem.id
                }?.count ?: 0)
            }.sortedWith(compareBy { it.categoryId })
            val newCategories = sortCategories(
                _categories.map { it.toCategoryModel() },
                newProducts
            )
            CatalogModel(
                categories = newCategories,
                products = newProducts
            )
        }.collect { catalog ->
            emit(catalog)
        }
    }

    private fun sortCategories(
        categories: List<CategoryModel>,
        products: List<ProductModel>
    ): List<CategoryModel> {
        val categoryOrder = products.map { it.categoryId }.distinct()
        return categoryOrder.mapNotNull { categoryId ->
            categories.find { it.id == categoryId }
        }
    }
}