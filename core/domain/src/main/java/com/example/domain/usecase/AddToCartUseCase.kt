package com.example.domain.usecase

import com.example.cart.repository.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend fun execute(id: Int){
        cartRepository.add(id)
    }
}