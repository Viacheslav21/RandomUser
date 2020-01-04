package com.example.user.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager(baseUrl: String, private val gson: Gson) {

    private val retrofit = retrofitBuilder(baseUrl).build()

    private fun retrofitBuilder(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient())


    private fun httpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    fun <T> getService(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }
}

