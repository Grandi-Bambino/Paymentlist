package ru.grandibambino.paymentlist.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AppToken(
    @SerializedName("token")
    @Expose
    val token: String
)