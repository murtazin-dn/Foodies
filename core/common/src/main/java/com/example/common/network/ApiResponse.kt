package com.example.common.network

sealed class ApiResponse <out T> {
    data class Success<T>(val value: T): ApiResponse<T>()
    data class Error(val error: NetworkError): ApiResponse<Nothing>()

    companion object{
        fun failure(error: NetworkError): Error = Error(error)

        fun <T> success(value: T): Success<T> = Success(value)
    }
}
