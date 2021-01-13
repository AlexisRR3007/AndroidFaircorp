package com.faircorp.api.heater

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Interface which defines the different calls to the API for heater
 */
interface HeaterApiService {

    @GET("heaters")
    fun findAll(): Call<List<HeaterDto>>

    @GET("heaters/{id}")
    fun findById(@Path("id") id: Long): Call<HeaterDto>

    @PUT("heaters/{id}/switch")
    fun switchStatusById(@Path("id") id: Long): Call<HeaterDto>

    @DELETE("heaters/{id}")
    fun deleteById(@Path("id") id: Long): Call<Void>

}