package com.example.data.model

import com.example.network.dto.CategoryDto

data class Category(
    val id: Int,
    val name: String
){
}
fun CategoryDto.toCategory(): Category {
    return Category(id = this.id, name = this.name)
}
