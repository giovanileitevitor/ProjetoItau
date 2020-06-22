package com.nm.infra.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nm.infra.livedata.observeSmart

fun <T : Any> LifecycleOwner.bindToVM(data: LiveData<T>, function: (id: T) -> Unit) {
    data.observeSmart(this) { function.invoke(it) }
}


