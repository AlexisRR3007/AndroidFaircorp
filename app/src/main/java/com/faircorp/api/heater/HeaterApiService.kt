package com.faircorp.api.heater

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.PUT

interface HeaterApiService {
    @GET("heaters")
    fun findAll(): Call<List<HeaterDto>>

    @GET("heaters/{id}")
    fun findById(@Path("id") id: Long): Call<HeaterDto>

    @PUT("heaters/{id}/switch")
    fun switchStatusById(@Path("id") id: Long): Call <HeaterDto>

    @DELETE("heaters/{id}")
    fun deleteById(@Path("id") id: Long): Call<Void>
}