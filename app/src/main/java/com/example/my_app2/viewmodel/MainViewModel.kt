package com.example.my_app2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.my_app2.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun calculate(amountStr: String, discountStr: String) {
        val amount = amountStr.toDoubleOrNull()
        val discount = discountStr.toDoubleOrNull()

        // Тексеру логикасы
        when {
            amount == null || discount == null -> {
                _uiState.value = UiState(errorText = "Сандарды толтырыңыз!", isValid = false)
            }
            discount < 0 || discount > 90 -> {
                _uiState.value = UiState(errorText = "Жеңілдік 0-90% арасында болуы керек", isValid = false)
            }
            else -> {
                val finalAmount = amount * (1 - discount / 100)
                _uiState.value = UiState(resultText = "Қорытынды: $finalAmount", isValid = true)
            }
        }
    }
}