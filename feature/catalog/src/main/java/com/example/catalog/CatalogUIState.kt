package com.example.catalog

import com.example.model.CatalogModel
import com.example.model.CategoryModel

sealed interface CatalogUIState {
    data object Loading: CatalogUIState
    data class Error(val message: String? = null): CatalogUIState
    sealed class Success: CatalogUIState {
        abstract val data: CatalogModel
        data class Data(override val data: CatalogModel): Success()
        data class EmptySearch(override val data: CatalogModel) : Success()
        data class EmptySearchValue(override val data: CatalogModel) : Success()
        data class EmptyFilter(override val data: CatalogModel) : Success()
    }

}