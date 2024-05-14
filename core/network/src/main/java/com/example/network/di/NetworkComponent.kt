package com.example.network.di

import android.app.Application
import com.example.network.FoodiesNetworkDataSource
import dagger.Component
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.OkHttpClient
import javax.inject.Singleton

@NetworkScope
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
//    val okHttpClient: OkHttpClient
//    val json: Json
    val dataSource: FoodiesNetworkDataSource
}