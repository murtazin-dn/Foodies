package com.example.catalog

import com.example.model.CatalogModel
import com.example.model.CategoryModel

sealed interface CatalogUIState {
    data object Loading: CatalogUIState
    data object EmptySearch: CatalogUIState
    data object EmptyFilter: CatalogUIState
    data class Error(val message: String? = null): CatalogUIState
    data class Success(val data: CatalogModel): CatalogUIState
}