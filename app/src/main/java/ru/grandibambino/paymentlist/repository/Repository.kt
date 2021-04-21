package ru.grandibambino.paymentlist.repository

import ru.grandibambino.paymentlist.data.DataResult

interface Repository {

    suspend fun auth(login: String, password: String) : DataResult
    suspend fun getAllPayments(token: String) : DataResult

}