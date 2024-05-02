package com.example.domain.usecase

import com.example.cart.repository.CartRepository
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend fun execute(id: Int){
        cartRepository.remove(id)
    }
}