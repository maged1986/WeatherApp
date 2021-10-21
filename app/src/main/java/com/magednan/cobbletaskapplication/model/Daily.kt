package com.magednan.cobbletaskapplication.model

data class Daily(
    val clouds: Double?,
    val dew_point: Double?,
    val dt: Int?,
    val feels_like: FeelsLike?,
    val humidity: Double?,
    val moon_phase: Double?,
    val moonrise: Double?,
    val moonset: Double?,
    val pop: Double?,
    val pressure: Double?,
    val rain: Double?,
    val sunrise: Double?,
    val sunset: Double?,
    val temp: Temp?,
    val uvi: Double?,
    val weather: List<WeatherX>?,
    val wind_deg: Int?,
    val wind_gust: Double?,
    val wind_speed: Double?
)