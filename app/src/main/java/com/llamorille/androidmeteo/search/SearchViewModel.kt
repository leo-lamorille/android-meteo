package com.llamorille.androidmeteo.search

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llamorille.androidmeteo.R
import com.llamorille.androidmeteo.model.WeatherMain
import com.llamorille.androidmeteo.api.ApiService
import com.llamorille.androidmeteo.model.SearchResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchViewModel() : ViewModel() {
    private val service = ApiService;
    private val _weather = MutableLiveData<WeatherMain>()
    val weather: LiveData<WeatherMain> = _weather

    private val _search = MutableLiveData<List<SearchResponse>>()
    val search: LiveData<List<SearchResponse>> = _search

    fun fetchWeatherByCity(city: String, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val weather = service.findWeatherByCity(city)
                _weather.postValue(weather)
            } catch (httpException: HttpException) {
                if (httpException.code() == 400) {
                    onError()
                } else {
                    println("Unhandled Http Exception")
                    httpException.printStackTrace();
                }
            }
        }
    }

    fun searchCity(city: String) {
        viewModelScope.launch {
            val search = service.searchCity(city)
            _search.postValue(search)
        }
    }
}