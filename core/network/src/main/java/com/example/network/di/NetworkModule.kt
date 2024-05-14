package com.example.network.di

import com.example.network.FoodiesNetworkDataSource
import com.example.network.retrofit.FoodiesRetrofit
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient


@Module
internal class NetworkModule {


    @Provides
    @NetworkScope
    fun provideOkHttpClient(): OkHttpClient{
        val client = OkHttpClient.Builder()
        return client.build()
    }

    @Provides
    @NetworkScope
    fun provideJsonConverterFactory(): Json{
        return Json
    }

    @Provides
    @NetworkScope
    fun provideNetworkDataSource(
        json: Json,
        okHttpClient: OkHttpClient
    ): FoodiesNetworkDataSource {
        return FoodiesRetrofit(
            json = json,
            okHttpClient = okHttpClient
        )
    }
}