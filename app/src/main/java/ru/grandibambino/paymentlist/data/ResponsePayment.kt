package ru.grandibambino.paymentlist.data

import com.google.gson.annotations.SerializedName

data class ResponsePayment(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("response")
    val response: List<Payment>,
    @SerializedName("error")
    val error: ErrorRequest
)