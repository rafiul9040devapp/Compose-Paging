package com.rafiul.composepaging.data.repository

import com.rafiul.composepaging.data.models.UserResponse
import com.rafiul.composepaging.data.network.UserApi
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UserApi) : UserRepository {
    override suspend fun getUsers(page: Int, limit: Int): UserResponse {
        delay(3000L)
        return api.getUsers(page = page, limit = limit)
    }
}