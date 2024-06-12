package com.example.model

data class CatalogModel(
    val categories: List<CategoryModel>,
    val products: List<ProductModel>,
    val tags: List<TagModel>,
    val sum: Long
)
