package com.rafiul.composepaging.data.di

import com.rafiul.composepaging.data.network.UserApi
import com.rafiul.composepaging.data.repository.UserRepository
import com.rafiul.composepaging.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(api: UserApi): UserRepository = UserRepositoryImpl(api)
}