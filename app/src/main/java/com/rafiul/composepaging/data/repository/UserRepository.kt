package com.rafiul.composepaging.data.repository

import com.rafiul.composepaging.data.models.UserResponse

interface UserRepository {
    suspend fun getUsers(page: Int, limit: Int): UserResponse
}