package com.pratiksha.weatherapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather") // endPoint
    fun getWeatherData(
        // urlParameter
        @Query("q")city: String,
        @Query("appid")appid: String,
    ): Call<WeatherCity>


}