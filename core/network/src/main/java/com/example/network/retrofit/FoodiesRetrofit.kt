package com.example.network.retrofit

import com.example.common.network.ApiResponse
import com.example.common.network.safeApiCall
import com.example.network.FoodiesNetworkDataSource
import com.example.network.dto.CategoryDto
import com.example.network.dto.ProductDto
import com.example.network.dto.TagDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FoodiesRetrofit @Inject constructor(
    json: Json,
    okHttpClient: OkHttpClient
) : FoodiesNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(FOODIES_BASE_URL)
        .client(okHttpClient)
        .build()
        .create(RetrofitFoodiesNetworkApi::class.java)
    override suspend fun getTags(): ApiResponse<List<TagDto>> =
        safeApiCall { networkApi.getTags() }


    override suspend fun getCategories(): ApiResponse<List<CategoryDto>> =
        safeApiCall { networkApi.getCategories() }

    override suspend fun getProducts(): ApiResponse<List<ProductDto>> =
        safeApiCall { networkApi.getProducts() }
}

private interface RetrofitFoodiesNetworkApi{

    @GET("Categories.json")
    suspend fun getCategories(): Response<List<CategoryDto>>

    @GET("Tags.json")
    suspend fun getTags(): Response<List<TagDto>>

    @GET("Products.json")
    suspend fun getProducts(): Response<List<ProductDto>>
}

private const val FOODIES_BASE_URL = "https://anika1d.github.io/WorkTestServer/"

