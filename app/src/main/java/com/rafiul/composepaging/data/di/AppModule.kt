package com.rafiul.composepaging.data.di

import com.rafiul.composepaging.data.network.UserApi
import com.rafiul.composepaging.data.repository.UserRepository
import com.rafiul.composepaging.data.repository.UserRepositoryImpl
import com.rafiul.composepaging.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    @Singleton
    @Provides
    fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Accept", "application/json")
                it.addHeader("app-id", "62cceaafb592b449c3aad899")
            }.build())
        }.build()
    }


    @Singleton
    @Provides
    fun getQuotesApi(retrofit: Retrofit.Builder, client: OkHttpClient): UserApi {
        return retrofit.client(client).build().create(UserApi::class.java)
    }

    @Provides
    fun provideUserRepository(api: UserApi): UserRepository = UserRepositoryImpl(api)
}