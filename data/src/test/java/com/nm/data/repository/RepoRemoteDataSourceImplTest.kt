package com.nm.data.repository

import com.nm.data.entity.Repo
import com.nm.data.entity.User
import com.nm.data.mapper.RepoResponseToListRepo
import com.nm.data.mapper.RepoSchemaToRepo
import com.nm.data.mapper.UserSchemaToUser
import com.nm.infra.net.api.ApiError
import com.nm.infra.net.api.ErrorResults
import com.nm.infra.net.api.Results
import com.nm.infra.net.api.SuccessResults
import com.nm.infra.net.data.RetrofitBuild
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepoRemoteDataSourceImplTest {
    private var mockWebServer: MockWebServer? = null

    private lateinit var repoRemoteDataSource: RepoRemoteDataSource

    @Before
    fun setupTest() {
        startMockWebServer()

        repoRemoteDataSource = RepoRemoteDataSourceImpl(
            RetrofitBuild.makeService(mockWebServer!!.url("/")),
            RepoResponseToListRepo(RepoSchemaToRepo(UserSchemaToUser()))
        )
    }

    @Test
    fun showSuccesfulResponse() = runBlocking {
        mockWebServer!!.enqueue(
            MockResponse().apply {
                val response = createWSResponse()
                setResponseCode(HTTP_OK).setBody(response)
            }
        )

        val res = repoRemoteDataSource.getReposRemote(1)

        assertEquals(createSuccessfulResults(), res)
    }

    private fun createWSResponse(): String {
        return StringBuilder().append(
            "{\n" +
                    "\t\"total_count\": 1473155,\n" +
                    "\t\"incomplete_results\": false,\n" +
                    "\t\"items\": [{\n" +
                    "\t\t\"id\": 121395510,\n" +
                    "\t\t\"node_id\": \"MDEwOlJlcG9zaXRvcnkxMjEzOTU1MTA=\",\n" +
                    "\t\t\"name\": \"CS-Notes\",\n" +
                    "\t\t\"full_name\": \"CyC2018/CS-Notes\",\n" +
                    "\t\t\"private\": false,\n" +
                    "\t\t\"owner\": {\n" +
                    "\t\t\t\"login\": \"CyC2018\",\n" +
                    "\t\t\t\"id\": 36260787,\n" +
                    "\t\t\t\"node_id\": \"MDQ6VXNlcjM2MjYwNzg3\",\n" +
                    "\t\t\t\"avatar_url\": \"https://avatars3.githubusercontent.com/u/36260787?v=4\",\n" +
                    "\t\t\t\"gravatar_id\": \"\",\n" +
                    "\t\t\t\"url\": \"https://api.github.com/users/CyC2018\",\n" +
                    "\t\t\t\"html_url\": \"https://github.com/CyC2018\",\n" +
                    "\t\t\t\"followers_url\": \"https://api.github.com/users/CyC2018/followers\",\n" +
                    "\t\t\t\"following_url\": \"https://api.github.com/users/CyC2018/following{/other_user}\",\n" +
                    "\t\t\t\"gists_url\": \"https://api.github.com/users/CyC2018/gists{/gist_id}\",\n" +
                    "\t\t\t\"starred_url\": \"https://api.github.com/users/CyC2018/starred{/owner}{/repo}\",\n" +
                    "\t\t\t\"subscriptions_url\": \"https://api.github.com/users/CyC2018/subscriptions\",\n" +
                    "\t\t\t\"organizations_url\": \"https://api.github.com/users/CyC2018/orgs\",\n" +
                    "\t\t\t\"repos_url\": \"https://api.github.com/users/CyC2018/repos\",\n" +
                    "\t\t\t\"events_url\": \"https://api.github.com/users/CyC2018/events{/privacy}\",\n" +
                    "\t\t\t\"received_events_url\": \"https://api.github.com/users/CyC2018/received_events\",\n" +
                    "\t\t\t\"type\": \"User\",\n" +
                    "\t\t\t\"site_admin\": false\n" +
                    "\t\t},\n" +
                    "\t\t\"html_url\": \"https://github.com/CyC2018/CS-Notes\",\n" +
                    "\t\t\"description\": \":books: 技术面试必备基础知识、Leetcode、计算机操作系统、计算机网络、系统设计、Java、Python、C++\",\n" +
                    "\t\t\"fork\": false,\n" +
                    "\t\t\"url\": \"https://api.github.com/repos/CyC2018/CS-Notes\",\n" +
                    "\t\t\"forks_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/forks\",\n" +
                    "\t\t\"keys_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/keys{/key_id}\",\n" +
                    "\t\t\"collaborators_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/collaborators{/collaborator}\",\n" +
                    "\t\t\"teams_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/teams\",\n" +
                    "\t\t\"hooks_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/hooks\",\n" +
                    "\t\t\"issue_events_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/issues/events{/number}\",\n" +
                    "\t\t\"events_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/events\",\n" +
                    "\t\t\"assignees_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/assignees{/user}\",\n" +
                    "\t\t\"branches_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/branches{/branch}\",\n" +
                    "\t\t\"tags_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/tags\",\n" +
                    "\t\t\"blobs_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/git/blobs{/sha}\",\n" +
                    "\t\t\"git_tags_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/git/tags{/sha}\",\n" +
                    "\t\t\"git_refs_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/git/refs{/sha}\",\n" +
                    "\t\t\"trees_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/git/trees{/sha}\",\n" +
                    "\t\t\"statuses_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/statuses/{sha}\",\n" +
                    "\t\t\"languages_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/languages\",\n" +
                    "\t\t\"stargazers_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/stargazers\",\n" +
                    "\t\t\"contributors_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/contributors\",\n" +
                    "\t\t\"subscribers_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/subscribers\",\n" +
                    "\t\t\"subscription_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/subscription\",\n" +
                    "\t\t\"commits_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/commits{/sha}\",\n" +
                    "\t\t\"git_commits_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/git/commits{/sha}\",\n" +
                    "\t\t\"comments_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/comments{/number}\",\n" +
                    "\t\t\"issue_comment_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/issues/comments{/number}\",\n" +
                    "\t\t\"contents_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/contents/{+path}\",\n" +
                    "\t\t\"compare_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/compare/{base}...{head}\",\n" +
                    "\t\t\"merges_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/merges\",\n" +
                    "\t\t\"archive_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/{archive_format}{/ref}\",\n" +
                    "\t\t\"downloads_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/downloads\",\n" +
                    "\t\t\"issues_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/issues{/number}\",\n" +
                    "\t\t\"pulls_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/pulls{/number}\",\n" +
                    "\t\t\"milestones_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/milestones{/number}\",\n" +
                    "\t\t\"notifications_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/notifications{?since,all,participating}\",\n" +
                    "\t\t\"labels_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/labels{/name}\",\n" +
                    "\t\t\"releases_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/releases{/id}\",\n" +
                    "\t\t\"deployments_url\": \"https://api.github.com/repos/CyC2018/CS-Notes/deployments\",\n" +
                    "\t\t\"created_at\": \"2018-02-13T14:56:24Z\",\n" +
                    "\t\t\"updated_at\": \"2020-06-19T20:24:59Z\",\n" +
                    "\t\t\"pushed_at\": \"2020-05-29T12:54:12Z\",\n" +
                    "\t\t\"git_url\": \"git://github.com/CyC2018/CS-Notes.git\",\n" +
                    "\t\t\"ssh_url\": \"git@github.com:CyC2018/CS-Notes.git\",\n" +
                    "\t\t\"clone_url\": \"https://github.com/CyC2018/CS-Notes.git\",\n" +
                    "\t\t\"svn_url\": \"https://github.com/CyC2018/CS-Notes\",\n" +
                    "\t\t\"homepage\": \"https://cyc2018.github.io/CS-Notes\",\n" +
                    "\t\t\"size\": 111772,\n" +
                    "\t\t\"stargazers_count\": 103921,\n" +
                    "\t\t\"watchers_count\": 103921,\n" +
                    "\t\t\"language\": \"Java\",\n" +
                    "\t\t\"has_issues\": true,\n" +
                    "\t\t\"has_projects\": true,\n" +
                    "\t\t\"has_downloads\": true,\n" +
                    "\t\t\"has_wiki\": true,\n" +
                    "\t\t\"has_pages\": true,\n" +
                    "\t\t\"forks_count\": 33893,\n" +
                    "\t\t\"mirror_url\": null,\n" +
                    "\t\t\"archived\": false,\n" +
                    "\t\t\"disabled\": false,\n" +
                    "\t\t\"open_issues_count\": 39,\n" +
                    "\t\t\"license\": null,\n" +
                    "\t\t\"forks\": 33893,\n" +
                    "\t\t\"open_issues\": 39,\n" +
                    "\t\t\"watchers\": 103921,\n" +
                    "\t\t\"default_branch\": \"master\",\n" +
                    "\t\t\"score\": 1.0\n" +
                    "\t}]\n" +
                    "}"
        ).toString()
    }

    private fun createSuccessfulResults(): Results<List<Repo>> {
        val repos = ArrayList<Repo>()

        repos.add(
                Repo(
                    id = 121395510L,
                    name = "CS-Notes",
                    full_name = "CyC2018/CS-Notes",
                    description = ":books: 技术面试必备基础知识、Leetcode、计算机操作系统、计算机网络、系统设计、Java、Python、C++",
                    forks_count = 33893L,
                    stargazers_count = 103921L,
                    language = "Java",
                    owner =  User(
                        login = "CyC2018",
                        avatar_url = "https://avatars3.githubusercontent.com/u/36260787?v=4",
                        type = "User"
                    ),
                    pulls_url = "https://api.github.com/repos/CyC2018/CS-Notes/pulls{/number}",
                    commits_url = "https://api.github.com/repos/CyC2018/CS-Notes/commits{/sha}"
                )
        )


        return SuccessResults(
            repos
        )
    }

    @Test
    fun remoteDataSourceUnSuccess() = runBlocking {
        mockWebServer!!.enqueue(
            MockResponse().apply {
                val response = createWSResponseError()
                setResponseCode(HTTP_ERROR).setBody(response)
            }
        )

        val res = repoRemoteDataSource.getReposRemote(1)

        assertEquals(createErrorResponse(), res)
    }

    private fun createErrorResponse(): Results<List<Repo>> {
        return ErrorResults(ApiError("400", "Client Error"))
    }

    private fun createWSResponseError(): String {
        return ""
    }


    @After
    fun tearDown() {
        finishMockWebServer()
    }


    private fun startMockWebServer() {
        if (mockWebServer == null) {
            mockWebServer = MockWebServer()
            mockWebServer?.start(MOCK_WEB_SERVER_PORT)
        }
    }

    private fun finishMockWebServer() {
        if (mockWebServer != null) {
            mockWebServer?.shutdown()
        }
    }


    companion object {
        const val MOCK_WEB_SERVER_PORT = 9091

        const val HTTP_OK = 200
        const val HTTP_ERROR = 400
    }
}