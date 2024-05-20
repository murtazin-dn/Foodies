package com.example.domain.mapper

import com.example.data.model.Product
import com.example.model.ProductModel

internal fun Product.toProductModel(countInCart: Int) = ProductModel(
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
    tags = this.tags.map { it.toTagModel() },
    categoryId = this.categoryId,
    countInCart = countInCart
)