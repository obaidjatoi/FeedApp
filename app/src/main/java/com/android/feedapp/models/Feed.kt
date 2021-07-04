package com.android.feedapp.models

import com.google.gson.annotations.SerializedName

data class Feed(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)