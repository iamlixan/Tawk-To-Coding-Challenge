package com.lixan.fajardo.tawkentranceexam.network.response

class RequestResult<out T>(private val value: Any) {

    val isSuccess: Boolean get() = value !is ResultError

    val isError: Boolean get() = value is ResultError

    fun result(): T = value as T

    fun error(): ResultError = value as ResultError

    companion object {
        fun <T : Any> success(value: T): RequestResult<T> = RequestResult(value)

        fun <T> error(error: ResultError): RequestResult<T> = RequestResult(error)

        fun <R, T> error(result: RequestResult<T>): RequestResult<R> = RequestResult(result.error())
    }
}