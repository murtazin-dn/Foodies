package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price_current: Long,
    val price_old: Long? = null,
    val category_id: Int,
    val measure: Int,
    val measure_unit: String,
    val energy_per_100_grams: Double,
    val proteins_per_100_grams: Double,
    val fats_per_100_grams: Double,
    val carbohydrates_per_100_grams: Double,
    val tag_ids: List<Int> = listOf()
)