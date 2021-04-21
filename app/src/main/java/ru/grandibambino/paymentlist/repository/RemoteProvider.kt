package ru.grandibambino.paymentlist.repository

import ru.grandibambino.paymentlist.repository.retrofit.ApiService

interface RemoteProvider {

    fun getApi() : ApiService

}