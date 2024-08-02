package com.example.tasks3application.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _sharedNumber = MutableLiveData<Int>()
    val sharedNumber: LiveData<Int> get() = _sharedNumber

    fun shareNumber(number: Int) {
        _sharedNumber.value = number
    }
}