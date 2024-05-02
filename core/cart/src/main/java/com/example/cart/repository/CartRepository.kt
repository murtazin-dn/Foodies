package com.example.cart.repository

import com.example.cart.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    val cart: Flow<List<CartItem>>
    fun add(id: Int)
    fun remove(id: Int)
}