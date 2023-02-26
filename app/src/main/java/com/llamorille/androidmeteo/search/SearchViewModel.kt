package com.llamorille.androidmeteo.search

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llamorille.androidmeteo.model.WeatherMain
import com.llamorille.androidmeteo.api.ApiService
import com.llamorille.androidmeteo.model.ForecastDay
import com.llamorille.androidmeteo.model.SearchResponse
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class SearchViewModel(): ViewModel() {
    private val service = ApiService;
    private val _weather = MutableLiveData<WeatherMain>()
    val weather: LiveData<WeatherMain> = _weather

    private val _search = MutableLiveData<List<SearchResponse>>()
    val search: LiveData<List<SearchResponse>> = _search

    private val _futureWeather = MutableLiveData<List<ForecastDay>?>()
    val futureWeather: LiveData<List<ForecastDay>?> = _futureWeather

    fun fetchWeatherByCity(city: String) {
        viewModelScope.launch {
            val weather = service.findWeatherByCity(city)
            _weather.postValue(weather)
        }
    }

    fun searchCity(city: String) {
        viewModelScope.launch {
            val search = service.searchCity(city)
            _search.postValue(search)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchFutureWeather(city: String) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val current = LocalDateTime.now().toString()
        Log.d("DATE", current)

        viewModelScope.launch {
            val future = service.searchFuture(city)
            if (future.forecast != null) {
                _futureWeather.postValue(future.forecast.forecastday)
            }
        }
    }
}