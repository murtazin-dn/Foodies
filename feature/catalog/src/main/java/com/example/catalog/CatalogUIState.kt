package com.example.catalog

import com.example.model.CatalogModel
import com.example.model.CategoryModel

sealed interface CatalogUIState {
    data object Loading: CatalogUIState
    data class Error(val message: String): CatalogUIState
    data class Success(val data: CatalogModel): CatalogUIState
}