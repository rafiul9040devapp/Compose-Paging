package com.rafiul.composepaging.data.network

import com.rafiul.composepaging.data.models.UserResponse
import com.rafiul.composepaging.utils.END_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET(END_POINT)
    suspend fun getUsers(@Query("page") page: Int, @Query("limit") limit: Int): UserResponse
}