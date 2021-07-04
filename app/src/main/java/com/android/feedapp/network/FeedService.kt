package com.android.feedapp.network

import com.android.feedapp.models.Feed
import com.android.feedapp.utils.URL
import retrofit2.http.GET
import retrofit2.http.Url

interface FeedService {
    @GET()
    suspend fun getFeeds(@Url() url: String = URL): Feed?

    companion object {
        fun getInstance(): FeedService {
            return RetroClient.ottRetrofit.create(FeedService::class.java)
        }
    }
}