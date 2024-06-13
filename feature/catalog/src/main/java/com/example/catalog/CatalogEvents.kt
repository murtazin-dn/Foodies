package com.example.catalog

import com.example.model.FilterModel

sealed interface CatalogEvents {
    data class Search(val value: String?): CatalogEvents
    data class Filter(val filter: List<FilterModel>): CatalogEvents
    data class AddToCart(val id: Int): CatalogEvents
    data class RemoveFromCartCart(val id: Int): CatalogEvents
    data object Reload: CatalogEvents
}