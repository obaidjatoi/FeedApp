package com.android.feedapp.view

import androidx.navigation.NavController
import com.android.feedapp.models.Result

fun navigateToDetails(navController : NavController , product : Result) {
    navController.navigate(FeedListingDirections.actionFeedListingToFeedDetail(product))
}