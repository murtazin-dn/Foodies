package com.example.productdetail

import com.example.model.ProductModel

sealed interface ProductDetailUIState {
    data object Loading : ProductDetailUIState
    data class Success(val product: ProductModel) : ProductDetailUIState
    data object Empty : ProductDetailUIState
    data class Error(val message: String? = null) : ProductDetailUIState

}