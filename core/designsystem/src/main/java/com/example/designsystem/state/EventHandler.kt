package com.example.designsystem.state

interface  EventHandler<T> {
    fun obtainEvent(event: T)
}