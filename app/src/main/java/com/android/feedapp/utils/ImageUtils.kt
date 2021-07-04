package com.android.feedapp.utils

import android.widget.ImageView
import com.android.feedapp.R
import com.squareup.picasso.Picasso

fun loadImageFromRemote(imageView : ImageView , url : String?){
    url?.let { u->
        Picasso.with(imageView.context).load(u).placeholder(R.drawable.loading).into(imageView)
    }

}

fun loadErrorImage(imageView : ImageView){
    Picasso.with(imageView.context).load(R.drawable.news).into(imageView)
}