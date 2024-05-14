package com.example.cart.di

import com.example.cart.repository.CartRepository
import com.example.cart.repository.CartRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class CartModule {

    @Provides
    @CartScope
    fun provideCartRepository(): CartRepository = CartRepositoryImpl()
}