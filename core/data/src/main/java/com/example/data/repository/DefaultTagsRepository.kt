package com.example.data.repository

import com.example.common.Result
import com.example.common.network.ApiResponse
import com.example.common.network.NetworkError
import com.example.data.model.Product
import com.example.data.model.Tag
import com.example.data.model.toProduct
import com.example.data.model.toTag
import com.example.network.FoodiesNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultTagsRepository @Inject constructor(
    private val dataSource: FoodiesNetworkDataSource
): TagsRepository {

    private var _tags = MutableStateFlow<Result<List<Tag>>>(Result.success(emptyList()))
    private val tags: StateFlow<Result<List<Tag>>> get() = _tags

    private var isDataLoaded = false

    private suspend fun ensureDataIsLoaded() {
        if (!isDataLoaded) {
            reloadTags()
        }
    }
    override suspend fun getTags(): Flow<Result<List<Tag>>> {
        ensureDataIsLoaded()
        return tags
    }

    override suspend fun reloadTags() {
        val newTags = withContext(Dispatchers.IO){
            val data = dataSource.getTags()
            when (data) {
                is ApiResponse.Success -> Result.success(data.value.map { it.toTag() })
                is ApiResponse.Error -> {
                    Result.failure(
                        when (data.error) {
                            is NetworkError.HttpError -> data.error.message
                            NetworkError.NetworkUnavailable -> data.error.message
                            NetworkError.Timeout -> data.error.message
                            is NetworkError.Unknown -> data.error.message
                        }
                    )
                }
            }
        }
        _tags.update { newTags }
        isDataLoaded = true
    }
}