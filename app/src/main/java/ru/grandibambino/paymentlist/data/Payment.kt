package ru.grandibambino.paymentlist.data

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("desc")
    val desc: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("created")
    val created: String
)