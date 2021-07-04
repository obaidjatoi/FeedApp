package com.android.feedapp.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Media(
    val approved_for_syndication: Int,
    val caption: String?,
    val copyright: String?,
    @SerializedName("media-metadata") val media_metadata: List<MediaMetadata>?,
    val subtype: String?,
    val type: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(MediaMetadata),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(approved_for_syndication)
        parcel.writeString(caption)
        parcel.writeString(copyright)
        parcel.writeTypedList(media_metadata)
        parcel.writeString(subtype)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Media> {
        override fun createFromParcel(parcel: Parcel): Media {
            return Media(parcel)
        }

        override fun newArray(size: Int): Array<Media?> {
            return arrayOfNulls(size)
        }
    }
}