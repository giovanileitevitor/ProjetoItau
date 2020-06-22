package com.nm.data.model.response

import android.os.Parcelable
import com.nm.data.entity.Detail
import com.nm.data.model.DetailSchema
import com.nm.data.model.RepoSchema
import com.nm.data.model.UserSchema
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsResponse(
    val details: List<DetailSchema>
) : Parcelable

