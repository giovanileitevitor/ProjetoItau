package com.nm.data.repository.services

import com.nm.data.model.DetailSchema
import com.nm.data.model.response.RepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GitServiceAPI {

    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Response<RepoResponse>

    @GET("repos/{login}/{name}/pulls")
    suspend fun getDetails(
        @Path("login") login: String,
        @Path("name") name: String
    ): Response<List<DetailSchema>>

}

/*
    Endpoint Tela Repositorio:
    - https://api.github.com/search/repositories?q=language:Java&sort=stars&%20page=1
    - Parametros:
        - language = "Kotlin"   (string)    (Default language = "java")
        - sort = "stars"        (string)    (Default parameter = "stars")
        - page = 1              (int)       (Default page = 1)


    Endpoint Tela Detalhes:
    - https://api.github.com/repos/CyC2018/CS-Notes/pulls
    - Parametros:
        - login = CyC2018   (string)
        - name = CS-Notes   (string)
 */