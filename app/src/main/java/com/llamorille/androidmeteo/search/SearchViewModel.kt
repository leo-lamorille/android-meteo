package com.llamorille.androidmeteo.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llamorille.androidmeteo.api.WeatherMain
import com.llamorille.androidmeteo.api.ApiService
import kotlinx.coroutines.launch

class SearchViewModel(): ViewModel() {
    private val service = ApiService;
    private val _weather = MutableLiveData<WeatherMain>()
    val weather: LiveData<WeatherMain> = _weather

    fun fetchWeatherByCity(city: String) {
        viewModelScope.launch {
            val weather = service.findWeatherByCity(city)
            _weather.postValue(weather)
        }
    }
}