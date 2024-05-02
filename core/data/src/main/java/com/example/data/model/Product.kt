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
    val tags: List<Tag> = listOf(),
    val categoryId: Int
){
}
fun ProductDto.toProduct(tags: List<TagDto>): Product {
    val tagList = tags.map { Tag(it.id, it.name) }
    return Product(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.image,
        priceCurrent = this.priceCurrent,
        priceOld = this.priceOld,
        measure = this.measure,
        measureUnit = this.measureUnit,
        energyPer100Grams = this.energyPer100Grams,
        proteinsPer100Grams = this.proteinsPer100Grams,
        fatsPer100Grams = this.fatsPer100Grams,
        carbohydratesPer100Grams = this.carbohydratesPer100Grams,
        tags = this.tagIds.mapNotNull { tagId ->
            tagList.find { it.id == tagId }
        },
        categoryId = this.categoryId
    )
}