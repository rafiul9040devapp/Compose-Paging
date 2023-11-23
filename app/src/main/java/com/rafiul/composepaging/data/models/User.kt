package com.rafiul.composepaging.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("title")
    val title: String
){
    val name: String
        get() = "$title $firstName $lastName"
}
