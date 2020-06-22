package com.nm.data.di

import com.nm.data.entity.Repo
import com.nm.data.entity.User
import com.nm.data.mapper.*
import com.nm.data.model.RepoSchema
import com.nm.data.model.UserSchema
import com.nm.infra.net.data.Mapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataModule {
    val mapperModule = module {

        single<Mapper<UserSchema, User>>(named("user")) { UserSchemaToUser() }
        single<Mapper<RepoSchema, Repo>>(named("repo")) {
            RepoSchemaToRepo(
                get(
                    named("user")
                )
            )
        }

        single(named("repo_response")) {
            RepoResponseToListRepo(
                get(
                    named("repo")
                )
            )
        }

        single(named("detail_response")) {
            DetailResponseToListDetail(
                get(
                    named("user")
                )
            )
        }
    }
}