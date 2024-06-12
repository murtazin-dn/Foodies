package com.example.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Result
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import com.example.model.CatalogModel
import com.example.model.ProductsModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CartViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : ViewModel() {

    val state: StateFlow<CartUIState> = getProductsUseCase.execute()
        .flatMapLatest<Result<ProductsModel>, CartUIState> { products ->
            when(products){
                is Result.Error -> flowOf(CartUIState.Error(message = products.message))
                is Result.Success -> {
                    val cart = products.value.copy(
                        products = products.value.products.filter { it.countInCart > 0 }
                    )
                    if (cart.products.isEmpty()) {
                        flowOf(CartUIState.Empty)
                    } else
                        flowOf(CartUIState.Success(cart))
                }
            }
        }
        .onStart { emit(CartUIState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CartUIState.Loading
        )

    fun addToCart(id: Int) {
        viewModelScope.launch { addToCartUseCase.execute(id) }
    }

    fun removeFromCart(id: Int) {
        viewModelScope.launch { removeFromCartUseCase.execute(id) }
    }

}