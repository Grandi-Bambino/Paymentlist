package ru.grandibambino.paymentlist.data

import com.google.gson.annotations.SerializedName

data class ErrorRequest(
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("error_msg")
    val errorMsg: String
)