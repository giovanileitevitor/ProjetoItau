package com.nm.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailSchema(
    val url: String,
    val id: Long,
    val number: Int,
    val state: String,
    val title: String,
    val user: UserSchema,
    val body: String
) : Parcelable