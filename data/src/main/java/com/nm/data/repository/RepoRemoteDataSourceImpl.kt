package com.nm.data.repository

import com.nm.data.entity.Detail
import com.nm.data.entity.Repo
import com.nm.data.mapper.DetailResponseToListDetail
import com.nm.data.mapper.RepoResponseToListRepo
import com.nm.data.repository.services.GitServiceAPI
import com.nm.infra.net.api.Results
import com.nm.infra.net.data.create

class RepoRemoteDataSourceImpl(
    private val gitServiceAPI: GitServiceAPI,
    private val mapperRepo: RepoResponseToListRepo,
    private val mapperDetail: DetailResponseToListDetail
) : RepoRemoteDataSource {

    override suspend fun getReposRemote(quantidade: Int): Results<List<Repo>> {
        return gitServiceAPI.getRepos("java", "stars", quantidade).create(mapperRepo)
    }

    override suspend fun getDetailRemote(login: String, name: String): Results<List<Detail>> {
        return gitServiceAPI.getDetails(login, name).create(mapperDetail)
    }
}