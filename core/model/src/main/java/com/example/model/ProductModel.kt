package com.example.model

data class ProductModel(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: Long,
    val priceOld: Long? = null,
    val measure: Int,
    val measureUnit: String,
    val energyPer100Grams: Double,
    val proteinsPer100Grams: Double,
    val fatsPer100Grams: Double,
    val carbohydratesPer100Grams: Double,
    val tags: List<TagModel> = listOf(),
    val categoryId: Int,
    val countInCart: Int
)
