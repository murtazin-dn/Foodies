package com.example.data.model

import com.example.network.dto.ProductDto
import com.example.network.dto.TagDto

data class Product(
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
    val tagIds: List<Int> = listOf(),
    val categoryId: Int
){
}
fun ProductDto.toProduct(): Product {
    return Product(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.image,
        priceCurrent = this.priceCurrent / 100,
        priceOld = this.priceOld?.let { it / 100 } ,
        measure = this.measure,
        measureUnit = this.measureUnit,
        energyPer100Grams = this.energyPer100Grams,
        proteinsPer100Grams = this.proteinsPer100Grams,
        fatsPer100Grams = this.fatsPer100Grams,
        carbohydratesPer100Grams = this.carbohydratesPer100Grams,
        tagIds = this.tagIds,
        categoryId = this.categoryId
    )
}