package com.example.cart.repository

import com.example.cart.model.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class CartRepositoryImpl: CartRepository {

    private var _cart = MutableStateFlow<List<CartItem>>(listOf())
    override val cart: Flow<List<CartItem>>
        get() = _cart

    override fun add(id: Int) {
        val newList = _cart.value.toMutableList()
        newList.find { it.id == id }?.let { cartItem ->
            val nevItem = cartItem.copy(count = cartItem.count + 1)
            newList.remove(cartItem)
            newList.add(nevItem)
        } ?: {
            newList.add(
                CartItem(
                    id = id,
                    count = 1
                )
            )
        }
        _cart.value = newList
    }

    override fun remove(id: Int) {
        val newList = _cart.value.toMutableList()
        newList.find { it.id == id }?.let { cartItem ->
            if (cartItem.count <= 1) newList.remove(cartItem)
            val nevItem = cartItem.copy(count = cartItem.count - 1)
            newList.remove(cartItem)
            newList.add(nevItem)
        }
        _cart.value = newList
    }
}