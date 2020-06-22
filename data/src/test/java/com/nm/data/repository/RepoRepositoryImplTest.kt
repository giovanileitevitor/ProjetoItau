package com.nm.data.repository

import com.nm.data.entity.Repo
import com.nm.data.entity.User
import com.nm.infra.net.api.ApiError
import com.nm.infra.net.api.ErrorResults
import com.nm.infra.net.api.Results
import com.nm.infra.net.api.SuccessResults
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepoRepositoryImplTest {

    @MockK
    lateinit var repoRemoteDataSource: RepoRemoteDataSource

    lateinit var repoRepository: RepoRepository

    @Before
    fun setupTest() {
        MockKAnnotations.init(this)

        repoRepository = RepoRepositoryImpl(repoRemoteDataSource)
    }

    @Test
    fun showPacientesSuccesfullResults() = runBlocking {
        coEvery {
            repoRemoteDataSource.getReposRemote(10)
        } returns createSuccessfulResults()

        val pacientes = repoRepository.getRepos(10)

        assertEquals(gerarList(), (pacientes as SuccessResults).body)
    }

    private fun gerarList(): ArrayList<Repo> {
        val repos = ArrayList<Repo>()

        for (i in 1..10) {
            repos.add(
                Repo(
                    id = i.toLong(),
                    name = "Name $i",
                    full_name = "full name $i",
                    description = "description $i",
                    forks_count = i.toLong(),
                    stargazers_count = i.toLong() * 2,
                    language = "language $i",
                    owner =  User(
                        login = "login $i",
                        avatar_url = "avatar url $i",
                        type = "type $i"
                    ),
                    pulls_url = "pulls url $i",
                    commits_url = "commits url $i"
                )
            )
        }

        return repos
    }

    private fun createSuccessfulResults(): Results<List<Repo>> {
        return SuccessResults(
            gerarList()
        )
    }

    @Test
    fun showPacientesUnSuccesfulResults() = runBlocking {
        coEvery {
            repoRemoteDataSource.getReposRemote(10)
        } returns createUnsuccessfulResults()

        val errorRes = repoRepository.getRepos(10)

        assertEquals(apiErrorResponse(), (errorRes as ErrorResults).error)
    }

    private fun createUnsuccessfulResults(): Results<List<Repo>> {
        return ErrorResults(
            apiErrorResponse()
        )
    }

    private fun apiErrorResponse(): ApiError {
        return ApiError(
            "20",
            "Erro Generico"
        )
    }

    @After
    fun tearDown() {
        //
    }
}