package com.nm.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Detail(
    val url: String,
    val id: Long,
    val number: Int,
    val state: String,
    val title: String,
    val user: User,
    val body: String
) : Parcelable