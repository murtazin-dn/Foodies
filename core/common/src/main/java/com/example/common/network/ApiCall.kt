package com.example.common.network

import android.net.http.HttpException
import retrofit2.Response
import java.io.IOException

suspend inline fun <T> safeApiCall(responseFunction: suspend () -> Response<T>): ApiResponse<T>{
    return try {
        val response = responseFunction.invoke()
        if (response.isSuccessful) {
            ApiResponse.success(response.body()!!)
        } else {
            ApiResponse.failure(NetworkError.HttpError(response.code(), response.message()))
        }
    } catch (e: IOException) {
        ApiResponse.failure(NetworkError.NetworkUnavailable)
    } catch (e: Exception) {
        ApiResponse.failure(NetworkError.Unknown(e))
    }
}