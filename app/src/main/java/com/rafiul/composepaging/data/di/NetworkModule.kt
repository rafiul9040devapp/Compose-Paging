package com.rafiul.composepaging.data.di

import com.rafiul.composepaging.data.network.UserApi
import com.rafiul.composepaging.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun getQuotesApi(retrofit: Retrofit.Builder, client: OkHttpClient): UserApi {
        return retrofit.client(client).build().create(UserApi::class.java)
    }

}