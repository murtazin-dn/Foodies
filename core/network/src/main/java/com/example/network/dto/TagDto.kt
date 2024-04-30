package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    val id: Int,
    val name: String
)
