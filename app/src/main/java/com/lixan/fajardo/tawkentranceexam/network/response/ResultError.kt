package com.lixan.fajardo.tawkentranceexam.network.response

data class ResultError(
    val errorMessage: String,
    val cause: Throwable? = null,
    val errorCode: String? = ""
)