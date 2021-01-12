package com.faircorp.model


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface WindowApiService {
    @GET("windows")
    fun findAll(): Call<List<WindowDto>>

    @GET("windows/{id}")
    fun findById(@Path("id") id: Long): Call<WindowDto>
}