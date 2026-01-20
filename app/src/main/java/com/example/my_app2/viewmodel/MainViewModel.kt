package com.example.my_app2.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _text = MutableStateFlow("Android")
    val text: StateFlow<String> = _text

    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun onButtonClick() {
        _text.value = "ViewModel"
    }

    fun incrementCounter() {
        _counter.value += 1
    }
}
