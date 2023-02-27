package com.llamorille.androidmeteo.search

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llamorille.androidmeteo.R
import com.llamorille.androidmeteo.api.ApiService
import com.llamorille.androidmeteo.model.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import retrofit2.HttpException

class SearchViewModel() : ViewModel() {
    private val service = ApiService;
    private val _weather = MutableLiveData<WeatherMain>()
    val weather: LiveData<WeatherMain> = _weather

    private val _search = MutableLiveData<List<SearchResponse>>()
    val search: LiveData<List<SearchResponse>> = _search

    private val _futureWeather = MutableLiveData<FutureWeather?>()
    val futureWeather: LiveData<FutureWeather?> = _futureWeather

    fun searchCity(city: String) {
        viewModelScope.launch {
            val search = service.searchCity(city)
            _search.postValue(search)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchFutureWeather(city: String, onError: () -> Unit) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val current = LocalDateTime.now().toString()
        Log.d("DATE", current)

        viewModelScope.launch {
            try {
                val future = service.searchFuture(city)
                if (future.forecast != null) {
                    _futureWeather.postValue(future)
                }
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
}
