package com.example.network.di

import android.app.Application
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
internal class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(application.cacheDir, cacheSize.toLong())
        return cache
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient{
        val client = OkHttpClient.Builder()
        client.cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideJsonConverterFactory(): Json{
        return Json
    }
}