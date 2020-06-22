package com.nm.repos.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nm.infra.base.BaseViewModel

class SplashActivityViewModel : BaseViewModel() {

    private val _next = MutableLiveData<Boolean>()

    val next: LiveData<Boolean> get() = _next

    fun getLongProcess() {
        launchDataLoad {

            android.os.Handler().postDelayed({
                _next.value = true
            }, 2000)
        }
    }
}