package com.android.feedapp.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("abstract") val abstract: String?,
    val adx_keywords: String?,
    val asset_id: Long,
    val byline: String?,
    val des_facet: List<String>?,
    val eta_id: Int,
    val geo_facet: List<String>?,
    val id: Long,
    val media: List<Media>?,
    val nytdsection: String?,
    val org_facet: List<String>?,
    val per_facet: List<String>?,
    val published_date: String?,
    val section: String?,
    val source: String?,
    val subsection: String?,
    val title: String?,
    val type: String?,
    val updated: String?,
    val uri: String?,
    val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readInt(),
        parcel.createStringArrayList(),
        parcel.readLong(),
        parcel.createTypedArrayList(Media),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(abstract)
        parcel.writeString(adx_keywords)
        parcel.writeLong(asset_id)
        parcel.writeString(byline)
        parcel.writeStringList(des_facet)
        parcel.writeInt(eta_id)
        parcel.writeStringList(geo_facet)
        parcel.writeLong(id)
        parcel.writeTypedList(media)
        parcel.writeString(nytdsection)
        parcel.writeStringList(org_facet)
        parcel.writeStringList(per_facet)
        parcel.writeString(published_date)
        parcel.writeString(section)
        parcel.writeString(source)
        parcel.writeString(subsection)
        parcel.writeString(title)
        parcel.writeString(type)
        parcel.writeString(updated)
        parcel.writeString(uri)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}