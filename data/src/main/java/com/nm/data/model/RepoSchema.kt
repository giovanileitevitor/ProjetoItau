package com.nm.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoSchema(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String,
    val forks_count: Long,
    val stargazers_count: Long,
    val language: String?,
    val owner : UserSchema,
    val pulls_url: String,
    val commits_url : String
) : Parcelable