package com.rafiul.composepaging.data.di

import com.rafiul.composepaging.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().also {
            it.addHeader("Accept", "application/json")
            it.addHeader("app-id", API_KEY)
        }
        return chain.proceed(request.build())
    }
}