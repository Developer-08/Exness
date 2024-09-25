package com.testing.exness

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class CountryViewModel : ViewModel() {
    private val _countries = mutableStateOf<List<Country>>(emptyList())
    val countries: State<List<Country>> = _countries

    private val _selectedCountry = mutableStateOf<Country?>(null)
    val selectedCountry: State<Country?> = _selectedCountry

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            try {
                val response = countryService.getCountries()
                _countries.value = response
            } catch (e: Exception) {
                // Handle error
                Log.e("CountryViewModel", "Error fetching countries", e)
            }
        }
    }

    fun selectCountry(country: Country) {
        _selectedCountry.value = country
    }
}

