package com.faircorp.api

import com.faircorp.api.heater.HeaterApiService
import com.faircorp.api.window.WindowApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
    val windowsApiService : WindowApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-alexis-renier-robin.cleverapps.io/api/")
            .build()
            .create(WindowApiService::class.java)
    }
    val heatersApiService : HeaterApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-alexis-renier-robin.cleverapps.io/api/")
            .build()
            .create(HeaterApiService::class.java)
    }
}