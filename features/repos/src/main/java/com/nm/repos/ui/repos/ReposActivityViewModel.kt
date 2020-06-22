package com.nm.repos.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nm.data.entity.Repo
import com.nm.data.repository.RepoRepository
import com.nm.infra.base.BaseViewModel
import com.nm.infra.net.api.ErrorResults
import com.nm.infra.net.api.SuccessResults


class ReposActivityViewModel(
    private val repoRepository: RepoRepository
) : BaseViewModel() {

    private val _repos = MutableLiveData<List<Repo>>()

    val repos: LiveData<List<Repo>> get() = _repos

    fun getRepositoriesList(page: Int) {
        launchDataLoad {
            when (val resposta = repoRepository.getRepos(page)) {
                is SuccessResults -> {
                    _repos.value = resposta.body
                }
                is ErrorResults -> {
                    _error.value = true
                }
                else -> {
                    _error.value = true
                }
            }
        }
    }

    override fun doOnError(throwable: Throwable) {
        super.doOnError(throwable)
        // Erro de Conexão
    }
}