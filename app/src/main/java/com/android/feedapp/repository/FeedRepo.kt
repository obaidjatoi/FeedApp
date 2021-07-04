package com.android.feedapp.repository

import com.android.feedapp.models.Feed
import com.android.feedapp.models.Result
import com.android.feedapp.network.FeedService

object FeedRepo {
    suspend fun getFeedResultListFromServer() : List<Result>? {
        try {
            val responseBody = FeedService.getInstance().getFeeds()
            responseBody?.let {
                return responseBody.results
            } ?: kotlin.run {
                return null
            }
        } catch (ee: java.lang.Exception) {
            return null
        }
    }

    suspend fun getFullFeedFromServer() : Feed? {
        try {
            val responseBody = FeedService.getInstance().getFeeds()
            responseBody?.let {
                return responseBody
            } ?: kotlin.run {
                return null
            }
        } catch (ee: java.lang.Exception) {
            return null
        }
    }
}