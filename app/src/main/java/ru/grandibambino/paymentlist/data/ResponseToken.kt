package ru.grandibambino.paymentlist.data

import com.google.gson.annotations.SerializedName

data class ResponseToken(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("response")
    val response: AppToken,
    @SerializedName("error")
    val error: ErrorRequest
)