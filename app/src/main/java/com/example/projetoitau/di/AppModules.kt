package com.example.projetoitau.di

import com.nm.data.repository.RepoRemoteDataSource
import com.nm.data.repository.RepoRemoteDataSourceImpl
import com.nm.data.repository.RepoRepository
import com.nm.data.repository.RepoRepositoryImpl
import com.nm.data.repository.services.GitServiceAPI
import com.nm.infra.net.data.RetrofitBuild
import com.nm.repos.ui.details.DetailsActivityViewModel
import com.nm.repos.ui.repos.ReposActivityViewModel
import com.nm.repos.ui.splash.SplashActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AppModules {

    const val apiService = "apiService"

    val serviceModules = module {
        single(named(apiService)) { RetrofitBuild.makeService<GitServiceAPI>("https://api.github.com/") }

        viewModel { SplashActivityViewModel() }
        viewModel { ReposActivityViewModel(get()) }
        viewModel { DetailsActivityViewModel(get()) }

        // Repositories
        single<RepoRemoteDataSource> {
            RepoRemoteDataSourceImpl(
                get(named(apiService)),
                get(named("repo_response")),
                get(named("detail_response"))
            )
        }

        single<RepoRepository> { RepoRepositoryImpl(get()) }
    }
}
