package com.nm.data.repository

import com.nm.data.entity.Detail
import com.nm.data.entity.Repo
import com.nm.infra.net.api.Results

interface RepoRepository {
    suspend fun getRepos(page: Int): Results<List<Repo>>
    suspend fun getDetail(login: String, name: String): Results<List<Detail>>
}