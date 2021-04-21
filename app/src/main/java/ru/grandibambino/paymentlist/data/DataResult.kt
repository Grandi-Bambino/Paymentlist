package ru.grandibambino.paymentlist.data

sealed class DataResult {

    class Success<out T>(val data: T) : DataResult()
    class Error(val error: Throwable) : DataResult()


}