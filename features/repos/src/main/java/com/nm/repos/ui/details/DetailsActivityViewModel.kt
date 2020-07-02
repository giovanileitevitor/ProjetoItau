package com.nm.repos.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nm.data.entity.Detail
import com.nm.data.repository.RepoRepository
import com.nm.infra.base.BaseViewModel
import com.nm.infra.net.api.ErrorResults
import com.nm.infra.net.api.SuccessResults

class DetailsActivityViewModel(
    private val repoRepository: RepoRepository
) : BaseViewModel() {

    private val _detail = MutableLiveData<List<Detail>>()

    val detail: LiveData<List<Detail>> get() = _detail

    fun getDETAIL(login: String, name: String) {
        launchDataLoad {
            when (val resposta = repoRepository.getDetail(login, name)) {
                is SuccessResults -> {
                    _detail.value = resposta.body
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
        // TODO - next phase
    }
}