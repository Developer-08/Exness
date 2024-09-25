package com.testing.exness

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.exness.api.RetrofitInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class CryptoViewModel : ViewModel() {

    private val _cryptoPrices = MutableStateFlow<Map<String, Cryptocurrency>?>(null)
    val cryptoPrices: StateFlow<Map<String, Cryptocurrency>?> = _cryptoPrices

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchCryptoPrices()
    }

    private fun fetchCryptoPrices() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCryptoPrices(symbols = "BTC,ETH,SOL,TRX,XRP,ADA")
                _cryptoPrices.value = response.data
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}

