package com.example.common.network

sealed class NetworkError : Throwable() {
    data object NetworkUnavailable : NetworkError()
    data object Timeout : NetworkError()
    data class HttpError(val code: Int, override val message: String?) : NetworkError()
    data class Unknown(val error: Throwable) : NetworkError()
}