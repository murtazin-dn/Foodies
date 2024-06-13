package com.example.domain.usecase

import com.example.data.repository.CategoriesRepository
import com.example.data.repository.ProductsRepository
import javax.inject.Inject

class ReloadCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
){
    suspend fun execute(){
        categoriesRepository.reloadCategories()
    }
}