package com.nm.data.repository

import com.nm.data.entity.Detail
import com.nm.data.entity.Repo
import com.nm.infra.net.api.Results

interface RepoRemoteDataSource {
    suspend fun getReposRemote(quantidade: Int): Results<List<Repo>>
    suspend fun getDetailRemote(login: String, name: String): Results<List<Detail>>
}