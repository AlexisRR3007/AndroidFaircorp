package com.faircorp.api

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
}