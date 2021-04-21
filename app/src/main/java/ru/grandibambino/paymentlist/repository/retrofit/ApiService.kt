package ru.grandibambino.paymentlist.repository.retrofit

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
import ru.grandibambino.paymentlist.data.Payment
import ru.grandibambino.paymentlist.data.ResponsePayment
import ru.grandibambino.paymentlist.data.ResponseToken

interface ApiService {

    @POST("login")
    @FormUrlEncoded
    fun authAsync(
        @HeaderMap headers: Map<String, String>,
        @Field("login") login: String = "demo",
        @Field("password") password: String = "12345"
    ): Deferred<ResponseToken>

    @GET("payments")
    fun getAllPaymentsAsync(
        @HeaderMap headers: Map<String, String>,
        @Query("token") token: String
    ): Deferred<ResponsePayment>

}