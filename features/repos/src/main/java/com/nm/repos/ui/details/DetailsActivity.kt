package com.nm.repos.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nm.data.entity.Detail
import com.nm.data.entity.Repo
import com.nm.infra.base.BaseActivity
import com.nm.infra.base.BaseViewModel
import com.nm.infra.lifecycle.bindToVM
import com.nm.repos.R
import com.nm.repos.ui.details.adapter.DetailsListAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_repositories.toolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity: BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel
    private val viewModel: DetailsActivityViewModel by viewModel()

    private lateinit var repo: Repo
    private lateinit var detailsListAdapter: DetailsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setupToolbar()
        setupRecycler()
        recoverParameters()
        setupActions()
        viewModel.getDETAIL(repo.owner.login, repo.name)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.main_details_toolbar_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupRecycler() {
        val decorator = DividerItemDecoration(recycler_view_details.context, DividerItemDecoration.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.divider_line))
        recycler_view_details.layoutManager = LinearLayoutManager(this)
        recycler_view_details.addItemDecoration(decorator)
    }

    private fun recoverParameters() {
        repo = intent.getParcelableExtra("repo")
    }

    private fun setupActions() {
        bindToVM(viewModel.loading, ::processLoading)
        bindToVM(viewModel.detail, ::processDetail)
    }

    private fun processLoading(loading: Boolean) {
        if (loading) {
            progress_bar_details.visibility = View.VISIBLE
            txt_repository_details.text = getString(R.string.repository_details, "?", "?")
        } else {
            progress_bar_details.visibility = View.GONE
        }
    }

    private fun processDetail(detail: List<Detail>) {
        txt_repository_details.text = getString(R.string.repository_details,
                                                        detail.size.toString(),
                                                        detail.size.toString())

        detailsListAdapter =
            DetailsListAdapter(
                this,
                R.layout.item_repository_details,
                detail,
                Glide.with(this)
            ) {
                Toast.makeText(this, "Pull Request date: " , Toast.LENGTH_SHORT).show()
            }

        recycler_view_details.adapter = detailsListAdapter
    }
}