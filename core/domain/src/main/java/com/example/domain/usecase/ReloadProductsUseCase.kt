package com.example.domain.usecase

import com.example.data.repository.ProductsRepository
import javax.inject.Inject

class ReloadProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
){
    suspend fun execute(){
        productsRepository.reloadProducts()
    }
}