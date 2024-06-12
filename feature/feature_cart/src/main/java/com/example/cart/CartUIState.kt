package com.example.cart

import com.example.model.ProductsModel

internal sealed interface CartUIState {
    data object Loading : CartUIState
    data class Success(val cart: ProductsModel) : CartUIState
    data class Error(val message: String? = null) : CartUIState
    data object Empty : CartUIState
}