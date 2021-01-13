package com.faircorp.api.room

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.PUT

interface RoomApiService {
    @GET("rooms")
    fun findAll(): Call<List<RoomDto>>

    @GET("rooms/{id}")
    fun findById(@Path("id") id: Long): Call<RoomDto>

    @PUT("rooms/{id}/openAllWindows")
    fun openAllWindowsById(@Path("id") id: Long): Call <RoomDto>

    @PUT("rooms/{id}/closeAllWindows")
    fun closeAllWindowsById(@Path("id") id: Long): Call <RoomDto>

    @PUT("rooms/{id}/onAllHeaters")
    fun onAllHeatersById(@Path("id") id: Long): Call <RoomDto>

    @PUT("rooms/{id}/offAllHeaters")
    fun offAllHeatersById(@Path("id") id: Long): Call <RoomDto>

    @DELETE("rooms/{id}")
    fun deleteById(@Path("id") id: Long): Call<Void>
}