package com.lixan.fajardo.tawkentranceexam.network.response.base

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    val message: String,
    @SerializedName("http_status") val httpStatusCode: Int,
    val success: Boolean,
    @SerializedName("error_code") val errorCode: String
)