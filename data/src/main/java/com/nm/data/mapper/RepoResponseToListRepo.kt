package com.nm.data.mapper

import com.nm.data.entity.Repo
import com.nm.data.model.RepoSchema
import com.nm.data.model.response.RepoResponse
import com.nm.infra.net.data.Mapper

class RepoResponseToListRepo(
    private val repoMapper: Mapper<RepoSchema, Repo>
) : Mapper<RepoResponse, List<Repo>>() {

    override fun transform(item: RepoResponse): List<Repo> {
        val repos = mutableListOf<Repo>()

        item.items.forEach {
            repos.add(
                repoMapper.transform(it)
            )
        }

        return repos
    }
}