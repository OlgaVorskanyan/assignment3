package com.example.homework3;

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
private val _weatherData = MutableLiveData<List<WeatherData>>()
        val weatherData: LiveData<List<WeatherData>> get() = _weatherData

        fun fetchWeatherForCities(cities: List<String>) {
        viewModelScope.launch {
        val data = weatherRepository.getWeatherForCities(cities)
        _weatherData.postValue(data)
        }
        }
        }
