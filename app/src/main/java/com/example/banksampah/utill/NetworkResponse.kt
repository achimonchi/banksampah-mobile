package com.example.banksampah.utill

import okhttp3.Headers

sealed class NetworkResponse<out T : Any, out U : Any> {
    /**      * A request that resulted in a response with a 2xx status code that has a body.      */
    data class Success<T : Any>(val body: T, val headers: Headers? = null) :
        NetworkResponse<T, Nothing>()

    /**      * A request that resulted in a response with a non-2xx status code.      */
    data class ServerError<U : Any>(val body: U?, val code: Int, val headers: Headers? = null)
}