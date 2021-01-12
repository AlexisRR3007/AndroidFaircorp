package com.faircorp.api.window


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.PUT

interface WindowApiService {
    @GET("windows")
    fun findAll(): Call<List<WindowDto>>

    @GET("windows/{id}")
    fun findById(@Path("id") id: Long): Call<WindowDto>

    @PUT("windows/{id}/switch")
    fun switchStatusById(@Path("id") id: Long): Call <WindowDto>

    @DELETE("windows/{id}")
    fun deleteById(@Path("id") id: Long): Call<Void>
}