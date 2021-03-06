package com.prike.tutorship.remote.service

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {
    const val BASE_URL = "http://tutorship.mcdir.ru/api/"

    fun makeService(isDebug: Boolean): ApiService = makeService(
        makeOkHttpClient(
            makeLoggingInterceptor(isDebug)
        ),
        Gson()
    )

    private fun makeService(okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiService::class.java)

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()

    private fun makeLoggingInterceptor(isDebug: Boolean) = HttpLoggingInterceptor().apply {
        level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }
}