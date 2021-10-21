 package com.magednan.cobbletaskapplication.repository

import android.util.Log
import com.magednan.cobbletaskapplication.model.Daily
import com.magednan.cobbletaskapplication.service.WeatherApi
import com.magednan.cobbletaskapplication.util.Constants.API_KEY
import javax.inject.Inject


 class MainRepository @Inject constructor(
         val weatherApi: WeatherApi
 ) {
     //getting Data From API
    suspend fun getData(latitude:Double, longitude:Double):List<Daily>{
        val response = weatherApi.getDailyForecast(latitude, longitude, "hourly", "imperial", API_KEY)
        val daily = response.body()!!.daily
        Log.d("TAG", "loadghhgh: " + daily)
        return daily?: emptyList()
    }
}