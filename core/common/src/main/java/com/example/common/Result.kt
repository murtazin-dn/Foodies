package com.example.common


sealed class Result <out T> {
    data class Success<T>(val value: T): Result<T>()
    data class Error(val message: String?): Result<Nothing>()

    companion object{
        fun <T> success(value: T): Success<T> = Success(value)
        fun failure(message: String?): Error = Error(message)
    }
}