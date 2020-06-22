package com.nm.repos.ui.splash

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.nm.infra.base.BaseActivity
import com.nm.infra.base.BaseViewModel
import com.nm.infra.lifecycle.bindToVM
import com.nm.infra.navagation.ActivityValues
import com.nm.infra.navagation.MobileActivityStarter
import com.nm.repos.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel
    private val viewModel: SplashActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        setupObservers()
        viewModel.getLongProcess()
    }

    private fun setupObservers() {
        bindToVM(viewModel.next, ::processNext)
    }

    private fun processNext(next: Boolean) {
        if (next) {
            MobileActivityStarter.activityStarter(
                this,
                ActivityValues.REPOS_ACTIVITY,
                Bundle()
            )
            finish()
        }
    }

}