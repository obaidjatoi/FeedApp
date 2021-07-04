package com.android.feedapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.android.feedapp.utils.hasInternetConnection

import com.android.feedapp.models.Result
import com.android.feedapp.repository.FeedRepo
import com.android.feedapp.view.ScreenState
import kotlinx.coroutines.launch

class FeedsViewModel(application: Application) : AndroidViewModel(application) {
    var screenState: MutableLiveData<ScreenState> = MutableLiveData()
    var data: ArrayList<Result> = ArrayList()

    fun getFeeds() {
        if (!hasInternetConnection(getApplication())) {
            screenState.value = ScreenState.NETWORK_ERROR
            return
        }

        screenState.value = ScreenState.DATA_LOADING
        viewModelScope.launch {
            val res = FeedRepo.getFeedResultListFromServer()
            res?.let { response ->
                data = response as ArrayList<Result>
                screenState.value = ScreenState.DATA_LOADED
            } ?: kotlin.run {
                notifyError()
            }
        }
    }

    private fun notifyError() {
        screenState.value = ScreenState.ERROR
    }
}