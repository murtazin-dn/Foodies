package com.example.data.model

import com.example.network.dto.TagDto

data class Tag(
    val id: Int,
    val name: String
){
}
fun TagDto.toTag(): Tag {
    return Tag(id = this.id, name = this.name)
}
