package com.example.domain.usecase

import com.example.cart.repository.CartRepository
import com.example.common.Result
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.ProductsRepository
import com.example.data.repository.TagsRepository
import com.example.domain.mapper.toCategoryModel
import com.example.domain.mapper.toProductModel
import com.example.domain.mapper.toTagModel
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
    private val tagsRepository: TagsRepository,
    private val cartRepository: CartRepository
) {
    fun execute(): Flow<Result<CatalogModel>> = flow {
        val categories = categoriesRepository.getCategories()
        val products = productsRepository.getProducts()
        val tags = tagsRepository.getTags()
        val cart = cartRepository.cart

        combine(
            cart,
            categories,
            products,
            tags
        ) { _cart, _categories, _products, _tags ->
            val newProducts = when (_products) {
                is Result.Error -> return@combine Result.failure(_products.message)
                is Result.Success -> {
                    _products.value.map { product ->
                        product.toProductModel(_cart.find { cartItem ->
                            product.id == cartItem.id
                        }?.count ?: 0)
                    }.sortedWith(compareBy { it.categoryId })
                }
            }
            val newCategories = when (_categories) {
                is Result.Error -> return@combine Result.failure(_categories.message)
                is Result.Success -> {
                    sortCategories(
                        _categories.value.map { it.toCategoryModel() },
                        newProducts
                    )
                }
            }
        val newTags = when(_tags) {
            is Result.Error -> return@combine Result.failure(_tags.message)
            is Result.Success -> {
                _tags.value.map { it.toTagModel() }
            }
        }
        val sum = newProducts.sumOf { it.priceCurrent * it.countInCart }
        Result.success(CatalogModel(
            categories = newCategories,
            products = newProducts,
            tags = newTags,
            sum = sum
        ))
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