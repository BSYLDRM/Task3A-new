package com.example.tasks3application.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _counter = MutableLiveData(0)
    val counterNum: LiveData<Int> get() = _counter

    fun incrementCount() {
        _counter.value = _counter.value?.plus(1)
    }
}