package com.nm.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val login: String,
    val avatar_url: String,
    val type: String
) : Parcelable