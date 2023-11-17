package com.example.homework3;

class WeatherRepository(private val weatherApiService: WeatherApiService) {
    suspend fun getWeatherForCities(cities: List<String>): List<WeatherData> {
        val weatherData = mutableListOf<WeatherData>()
        cities.forEach { city ->
                val response = weatherApiService.getWeather(BuildConfig.WEATHER_API_KEY, city)
            weatherData.add(response)
        }
        return weatherData
    }
}
