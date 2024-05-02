package com.example.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,

    @SerialName("price_current")
    val priceCurrent: Long,

    @SerialName("price_old")
    val priceOld: Long? = null,

    @SerialName("category_id")
    val categoryId: Int,

    val measure: Int,

    @SerialName("measure_unit")
    val measureUnit: String,

    @SerialName("energy_per_100_grams")
    val energyPer100Grams: Double,

    @SerialName("proteins_per_100_grams")
    val proteinsPer100Grams: Double,

    @SerialName("fats_per_100_grams")
    val fatsPer100Grams: Double,

    @SerialName("carbohydrates_per_100_grams")
    val carbohydratesPer100Grams: Double,

    @SerialName("tag_ids")
    val tagIds: List<Int> = emptyList()
)