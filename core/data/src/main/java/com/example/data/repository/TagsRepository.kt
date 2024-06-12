package com.example.data.repository

import com.example.common.Result
import com.example.data.model.Product
import com.example.data.model.Tag
import kotlinx.coroutines.flow.Flow

interface TagsRepository {
    suspend fun getTags(): Flow<Result<List<Tag>>>
    suspend fun reloadTags()
}