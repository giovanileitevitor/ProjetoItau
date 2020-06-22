package com.nm.repos.ui.repos

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nm.data.entity.Repo
import com.nm.infra.base.BaseActivity
import com.nm.infra.base.BaseViewModel
import com.nm.infra.lifecycle.bindToVM
import com.nm.repos.R
import com.nm.repos.ui.details.DetailsActivity
import com.nm.repos.ui.repos.adapter.RepoListAdapter
import kotlinx.android.synthetic.main.activity_repositories.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel
    private val viewModel: ReposActivityViewModel by viewModel()

    private var position: Int = 1
    private lateinit var repositoriesListAdapter: RepoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        setupToolbar()
        setupRecycler()
        setupActions()
        setupListeners()

        //Executa a chamada no endpoint para trazer a lista de repositorios
        viewModel.getRepositoriesList(position)

    }

    private fun setupActions(){
        bindToVM(viewModel.error, ::showError)
        bindToVM(viewModel.loading, ::processLoading)
        bindToVM(viewModel.repos, ::processRepos)
    }

    private fun showError(error: Boolean){
        if (error) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun processLoading(loading: @ParameterName(name = "id") Boolean) {
        if (loading) {
            progress_bar.visibility = View.VISIBLE
            supportActionBar?.title = getString(R.string.main_repositories_toolbar_title, "-")
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun setupRecycler() {
        val decorator = DividerItemDecoration(recycler_view_repositories.context, DividerItemDecoration.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.divider_line))
        recycler_view_repositories.layoutManager = LinearLayoutManager(this)
        recycler_view_repositories.addItemDecoration(decorator)
    }

    private fun processRepos(repos: List<Repo>) {
        supportActionBar?.title = getString(R.string.main_repositories_toolbar_title, repos.size.toString())

        repositoriesListAdapter =
            RepoListAdapter(
                this,
                R.layout.item_repository_list,
                repos,
                Glide.with(this)
            ) {
                callRepositoryDetails(it)
            }

        recycler_view_repositories.adapter = repositoriesListAdapter
    }

    private fun callRepositoryDetails(repo: Repo) {
        val intent = Intent(this, DetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("repo", repo)
        bundle.putInt("page", position)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.main_repositories_toolbar_title, "?")
        txt_total_repositories.text = getString(R.string.paginaAtual, position.toString())
    }

    private fun setupListeners(){

        btn_previous.setOnClickListener {
            if(position <= 1){
                position = 1
                txt_total_repositories.text = getString(R.string.paginaAtual, position.toString())
            }else{
                position -= 1
                txt_total_repositories.text = getString(R.string.paginaAtual, position.toString())
                viewModel.getRepositoriesList(position)
            }
        }

        btn_next.setOnClickListener {
            if(position >= 99){
                position = 99
                txt_total_repositories.text = getString(R.string.paginaAtual, position.toString())
            }else{
                position += 1
                txt_total_repositories.text = getString(R.string.paginaAtual, position.toString())
                viewModel.getRepositoriesList(position)
            }
        }
    }
}