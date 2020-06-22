package com.nm.data.repository

import com.nm.data.entity.Detail
import com.nm.data.entity.Repo
import com.nm.infra.net.api.Results

class RepoRepositoryImpl(
    private val repoRemoteDataSource: RepoRemoteDataSource
) : RepoRepository {

    override suspend fun getRepos(page: Int): Results<List<Repo>> {
        return repoRemoteDataSource.getReposRemote(page)
    }

    override suspend fun getDetail(login: String, name: String): Results<List<Detail>> {
        return repoRemoteDataSource.getDetailRemote(login, name)
    }
}