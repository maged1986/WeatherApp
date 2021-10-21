package com.magednan.cobbletaskapplication.service

import com.magednan.cobbletaskapplication.model.WeatherData
import com.magednan.cobbletaskapplication.util.Constants.DAILY_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(DAILY_URL)
    suspend fun getDailyForecast(
        @Query("lat")
         latitude: Double ,
         @Query("lon")
         longitude: Double ,
         @Query("exclude")
         exclude: String ,
         @Query("units")
         units: String ,
         @Query("apiKey")
         apiKey: String
    ): Response<WeatherData>
}