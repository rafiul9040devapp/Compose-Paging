package com.rafiul.composepaging.data.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val users: List<User>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total")
    val total: Int
)
