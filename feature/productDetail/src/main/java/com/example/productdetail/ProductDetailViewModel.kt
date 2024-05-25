package com.example.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import com.example.model.CatalogModel
import com.example.model.ProductModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @AssistedInject constructor(
    @Assisted private val id: Int,
    private val getProductUseCase: GetProductUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : ViewModel() {

    val state: StateFlow<ProductDetailUIState> = getProductUseCase.execute(id)
        .flatMapLatest { product ->
            if (product == null) {
                flowOf(ProductDetailUIState.Empty)
            } else
                flowOf(ProductDetailUIState.Success(product))
        }
        .onStart { emit(ProductDetailUIState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductDetailUIState.Loading
        )

    fun addToCart() {
        viewModelScope.launch { addToCartUseCase.execute(id) }
    }
    fun reload() {
        viewModelScope.launch {  }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): ProductDetailViewModel

    }
}
interface ProductDetailViewModelFactory {
    fun create(id: Int): ProductDetailViewModel
}