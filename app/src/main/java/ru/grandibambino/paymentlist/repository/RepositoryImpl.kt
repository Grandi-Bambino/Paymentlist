package ru.grandibambino.paymentlist.repository

import ru.grandibambino.paymentlist.data.DataResult
import ru.grandibambino.paymentlist.utils.APP_TOKEN
import ru.grandibambino.paymentlist.utils.TAG_REPO
import ru.grandibambino.paymentlist.utils.logError

class RepositoryImpl(private val remoteProvider: RemoteProvider) : Repository {
    private val headers = mapOf(
        "app-key" to "12345",
        "v" to "1"
    )

    override suspend fun auth(login: String, password: String): DataResult {

        val response =
            remoteProvider.getApi().authAsync(headers = headers, login = login, password = password)
                .await()
        return if (response.success) {
            APP_TOKEN = response.response.token
            DataResult.Success(response.success)
        } else {
            logError(TAG_REPO, response.error.errorMsg)
            DataResult.Error(Throwable(response.error.errorMsg))
        }

    }

    override suspend fun getAllPayments(token: String) : DataResult {

        val response =
            remoteProvider.getApi().getAllPaymentsAsync(headers = headers, token = token).await()
        return if (response.success){
            DataResult.Success(response.response)
        }else{
            DataResult.Error(Throwable(response.error.errorMsg))
        }
    }
}