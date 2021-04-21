package ru.grandibambino.paymentlist.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.grandibambino.paymentlist.data.DataResult
import ru.grandibambino.paymentlist.repository.Repository
import ru.grandibambino.paymentlist.utils.APP_TOKEN
import ru.grandibambino.paymentlist.utils.TAG_PAYMENTS_VIEW_MODEL
import ru.grandibambino.paymentlist.utils.logError

class PaymentsViewModel(private val repository: Repository) : ViewModel() {

    private val paymentsCoroutineScope = CoroutineScope(
        Dispatchers.Main +
                SupervisorJob() +
                CoroutineExceptionHandler { _, throwable ->
                    handlerError(throwable)
                }
    )

    private val _paymentsLiveData = MutableLiveData<DataResult>()
    val paymentsLiveData = _paymentsLiveData

    fun getAllPayments() {
        paymentsCoroutineScope.launch {
            _paymentsLiveData.postValue(APP_TOKEN?.let { repository.getAllPayments(it) })
        }
    }

    private fun handlerError(throwable: Throwable) {
        logError(TAG_PAYMENTS_VIEW_MODEL, throwable.message.toString())
    }

}