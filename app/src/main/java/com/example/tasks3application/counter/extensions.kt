package com.example.tasks3application.counter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, Observer { value -> value?.let { observer(it) } })
}