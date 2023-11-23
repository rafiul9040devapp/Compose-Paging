package com.rafiul.composepaging.data.repository

import com.rafiul.composepaging.data.models.UserResponse
import com.rafiul.composepaging.data.network.UserApi
import com.rafiul.composepaging.utils.NETWORK_DELAY
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UserApi) : UserRepository {
    override suspend fun getUsers(page: Int, limit: Int): UserResponse {
        delay(NETWORK_DELAY)
        return api.getUsers(page = page, limit = limit)
    }
}