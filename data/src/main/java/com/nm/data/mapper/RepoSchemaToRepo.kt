package com.nm.data.mapper

import com.nm.data.entity.Repo
import com.nm.data.entity.User
import com.nm.data.model.RepoSchema
import com.nm.data.model.UserSchema
import com.nm.infra.net.data.Mapper

class RepoSchemaToRepo(
    private val userMapper: Mapper<UserSchema, User>
) : Mapper<RepoSchema, Repo>() {

    override fun transform(item: RepoSchema): Repo {
        return Repo(
            id = item.id,
            name = item.name,
            full_name = item.full_name,
            description = item.description,
            forks_count = item.forks_count,
            stargazers_count = item.stargazers_count,
            language = item.language ?: "No Language",
            owner = userMapper.transform(item.owner),
            pulls_url = item.pulls_url,
            commits_url = item.commits_url
        )
    }
}