package com.example.domain.mapper

import com.example.cart.model.CartItem
import com.example.data.model.Category
import com.example.data.model.Product
import com.example.model.CategoryModel

internal fun Category.toCategoryModel(): CategoryModel {
    return CategoryModel(
        id = this.id,
        name = this.name
    )
}