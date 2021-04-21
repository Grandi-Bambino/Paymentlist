package ru.grandibambino.paymentlist.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.grandibambino.paymentlist.data.DataResult
import ru.grandibambino.paymentlist.repository.Repository
import ru.grandibambino.paymentlist.utils.TAG_AUTH_VIEW_MODEL
import ru.grandibambino.paymentlist.utils.logError

class AuthViewModel(private val repository: Repository) : ViewModel() {

    lateinit var login: String
    lateinit var password: String

    private val _authLiveData = MutableLiveData<DataResult>()
    val authLiveData = _authLiveData

    private val authCoroutineScope = CoroutineScope(
        Dispatchers.IO +
                SupervisorJob() +
                CoroutineExceptionHandler { _, throwable ->
                    handlerError(throwable)
                }
    )

    fun setLogin(login: String) = login.also { this.login = it }
    fun setPassword(password: String) = password.also { this.password = it }

    fun auth() {
        authCoroutineScope.launch {
            _authLiveData.postValue(repository.auth(login, password))
        }
    }

    override fun onCleared() {
        super.onCleared()
        authCoroutineScope.coroutineContext.cancelChildren()
    }

    private fun handlerError(throwable: Throwable) {
        logError(TAG_AUTH_VIEW_MODEL, throwable.message.toString())
    }

}