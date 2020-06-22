package com.nm.data.model.response

import android.os.Parcelable
import com.nm.data.model.RepoSchema
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoResponse (
    val total_count: Long,
    val incomplete_results: Boolean,
    val items: List<RepoSchema>
) : Parcelable

