package com.example.domain.mapper

import com.example.cart.model.CartItem
import com.example.data.model.Category
import com.example.data.model.Product
import com.example.model.CategoryModel

internal fun Category.toCategoryModel(products: List<Product>, cart: List<CartItem>): CategoryModel {
    return CategoryModel(
        name = this.name,
        products = products.filter {
            it.categoryId == this.id
        }.map { product ->
            val countInCart = cart.find { it.id == product.id }?.count ?: 0
            product.toProductModel(countInCart)
        }
    )
}