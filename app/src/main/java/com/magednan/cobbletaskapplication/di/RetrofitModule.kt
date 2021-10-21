package com.magednan.cobbletaskapplication.di

import com.magednan.cobbletaskapplication.service.WeatherApi
import com.magednan.cobbletaskapplication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
//To get Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .callTimeout(200L, TimeUnit.SECONDS)
                .build()
        val retrofit= Retrofit.Builder()
                .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        return retrofit
    }
    //To get Api instance
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): WeatherApi {
        val api= retrofit.create(WeatherApi::class.java)
        return api
    }
}