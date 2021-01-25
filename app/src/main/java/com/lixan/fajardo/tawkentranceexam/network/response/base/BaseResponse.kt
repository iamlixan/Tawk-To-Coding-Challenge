package com.lixan.fajardo.tawkentranceexam.network.response.base

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("message") val message: String? = "",
    @SerializedName("documentation_url") val documentationURL: String = ""
)