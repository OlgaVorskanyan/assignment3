package com.example.homework3
interface WeatherApiService {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ): WeatherResponse
}

// Retrofit instance
val retrofit = Retrofit.Builder()
    .baseUrl("https://app.swaggerhub.com/apis-docs/WeatherAPI.com/WeatherAPI/1.0.2-oas3-oas3.1-oas3.1/#/APIs/realtime-weather")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service = retrofit.create(WeatherApiService::class.java)

suspend fun fetchWeatherForCity(city: String) {
    try {
        val response = service.getWeather("9c3fda368cba4db6b1d214911231711", city)
    } catch (e: Exception) {
    }
}
