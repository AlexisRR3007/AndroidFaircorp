package com.faircorp.api.room

import com.faircorp.api.heater.HeaterDto
import com.faircorp.api.window.WindowDto
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Interface which defines the different calls to the API for room
 */
interface RoomApiService {

    @GET("rooms")
    fun findAll(): Call<List<RoomDto>>

    @GET("rooms/{id}")
    fun findById(@Path("id") id: Long): Call<RoomDto>

    @GET("rooms/{id}/windows")
    fun findAllWindowsFromRoom(@Path("id") id: Long): Call<List<WindowDto>>

    @GET("rooms/{id}/heaters")
    fun findAllHeatersFromRoom(@Path("id") id: Long): Call<List<HeaterDto>>

    @PUT("rooms/{id}/openAllWindows")
    fun openAllWindowsById(@Path("id") id: Long): Call<RoomDto>

    @PUT("rooms/{id}/closeAllWindows")
    fun closeAllWindowsById(@Path("id") id: Long): Call<RoomDto>

    @PUT("rooms/{id}/onAllHeaters")
    fun onAllHeatersById(@Path("id") id: Long): Call<RoomDto>

    @PUT("rooms/{id}/offAllHeaters")
    fun offAllHeatersById(@Path("id") id: Long): Call<RoomDto>

    @DELETE("rooms/{id}")
    fun deleteById(@Path("id") id: Long): Call<Void>

}