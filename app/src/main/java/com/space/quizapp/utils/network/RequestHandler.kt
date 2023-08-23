package com.space.quizapp.utils.network

import retrofit2.Response

abstract class RequestHandler {
    suspend fun <T> apiCall(request:suspend () -> Response<T>): RequestResource<T> {
        return try {
            val response = request()
            if (response.isSuccessful){
                RequestResource.success(response.body()!!)
            }else{
                RequestResource.error(response.message())
            }
        }catch (e: Exception){
            RequestResource.error(e.message ?: "error")
        }
    }
}