package com.example.tasks3application.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _secretNumber = MutableLiveData<Int>()
    val secretNumber: LiveData<Int> get() = _secretNumber
    private val _randomChar = MutableLiveData<Char>()
    val randomChar: LiveData<Char> get() = _randomChar
    private val _resultMessage = MutableLiveData<String>()
    val resultMessage: LiveData<String> get() = _resultMessage
    private val _turnsCount = MutableLiveData<Int>().apply { value = 0 }
    val turnsCount: LiveData<Int> get() = _turnsCount

    init {
        startGame()
        _turnsCount.value = 0
    }

    fun startGame() {
        _secretNumber.value = (0..9).random()
        _randomChar.value = ('A'..'Z').random()
        _turnsCount.value = 0
    }

    fun checkGuess(guess: Int, winMessage: String, againMessage: String) {
        _turnsCount.value = (_turnsCount.value ?: 0) + 1
        if (guess == _secretNumber.value) {
            _resultMessage.value = winMessage
            _randomChar.value = _secretNumber.value.toString().first()
            startGame()
        } else {
            _resultMessage.value = againMessage
        }
    }
}