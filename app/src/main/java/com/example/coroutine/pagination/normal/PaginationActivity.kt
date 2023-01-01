package com.example.coroutine.pagination.normal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.applaunch.appbase.view_base.BaseActivity
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.example.coroutine.R
import com.example.coroutine.databinding.ActivityPaginationBinding
import com.example.coroutine.pagination.normal.viewmodel.PaginationViewModel

class PaginationActivity : BaseActivity<PaginationViewModel, ActivityPaginationBinding>() {
    private lateinit var navController: NavController

    override val mViewModel: PaginationViewModel by viewModels()
    override val layoutId: Int = R.layout.activity_pagination
    override val headerData: Triple<String, String, String>
        get() {
            val accessToken = "tokan"
            val apiKey = "BuildConfig.X_API_KEY_STAGING"
            val appVersion = "BuildConfig.APP_VERSION"
            return Triple(accessToken, apiKey, appVersion)
        }

    override fun onInitialize() {
        val mainNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = mainNavHostFragment.navController
    }

    override fun subscribeObserver() {
    }
}