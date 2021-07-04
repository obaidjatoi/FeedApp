package com.android.feedapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient {

    val ottRetrofit by lazy { invoke("https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/dynamodb-writer/") }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    operator fun invoke(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}