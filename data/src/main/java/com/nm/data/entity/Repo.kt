package com.nm.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String,
    val forks_count: Long,
    val stargazers_count: Long,
    val language: String,
    val owner : User,
    val pulls_url: String,
    val commits_url : String
) : Parcelable