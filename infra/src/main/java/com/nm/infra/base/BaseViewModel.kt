package com.nm.infra.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

abstract class BaseViewModel() : DefaultViewModel() {

    val loading: LiveData<Boolean> get() = _loading

    val error: LiveData<Boolean> get() = _error

    protected val _loading =
        MutableLiveData<Boolean>()

    protected val _error =
        MutableLiveData<Boolean>()

    protected fun launchDataLoad(
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                _loading.value = true
                block()
            } catch (error: Exception) {
                doOnError(error)
            } finally {
                _loading.value = false
            }
        }
    }

    open fun doOnError(throwable: Throwable) {
        val tt = throwable.message
    }
}