package com.nm.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSchema(
    val login: String,
    val avatar_url: String,
    val type: String
) : Parcelable