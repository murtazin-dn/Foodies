package com.example.domain.usecase

import com.example.data.repository.CategoriesRepository
import com.example.data.repository.TagsRepository
import javax.inject.Inject

class ReloadTagsUseCase @Inject constructor(
    private val tagsRepository: TagsRepository
){
    suspend fun execute(){
        tagsRepository.reloadTags()
    }
}