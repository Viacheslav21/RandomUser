package com.example.user.data.remote

import com.example.user.objects.UserObject
import retrofit2.http.GET
import retrofit2.http.Query

interface MainScreenApi {
    @GET("/api/")
    suspend fun getUsers(@Query("results") amount: Int = 20, @Query("page") page: Int): UserObject
}