package com.faircorp.api

import com.faircorp.api.heater.HeaterApiService
import com.faircorp.api.room.RoomApiService
import com.faircorp.api.window.WindowApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Class which define on which URL call the API
 */
class ApiServices {

    val windowsApiService: WindowApiService by lazy {
        Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://faircorp-alexis-renier-robin.cleverapps.io/api/")
                .build()
                .create(WindowApiService::class.java)
    }

    val heatersApiService: HeaterApiService by lazy {
        Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://faircorp-alexis-renier-robin.cleverapps.io/api/")
                .build()
                .create(HeaterApiService::class.java)
    }

    val roomsApiService: RoomApiService by lazy {
        Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://faircorp-alexis-renier-robin.cleverapps.io/api/")
                .build()
                .create(RoomApiService::class.java)
    }

}