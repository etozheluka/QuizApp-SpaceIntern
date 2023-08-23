package com.space.quizapp.utils.network

data class RequestResource<out T>(
    val status: Status? = null,
    val data: T? = null,
    val message: String? = ""
) {

    enum class Status {
        SUCCESS,
        ERROR,
    }

    companion object {
        fun <T> success(data: T): RequestResource<T> {
            return RequestResource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): RequestResource<T> {
            return RequestResource(Status.ERROR, data, message)
        }
    }
}