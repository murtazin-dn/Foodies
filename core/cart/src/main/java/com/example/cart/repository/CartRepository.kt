package com.example.cart.repository

import com.example.cart.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    val cart: Flow<List<CartItem>>
    suspend fun add(id: Int)
    suspend fun remove(id: Int)
}