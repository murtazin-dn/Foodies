package com.example.cart.di

import com.example.cart.repository.CartRepository
import dagger.Component

@CartScope
@Component(modules = [CartModule::class])
interface CartComponent {
    val cartRepository: CartRepository
}