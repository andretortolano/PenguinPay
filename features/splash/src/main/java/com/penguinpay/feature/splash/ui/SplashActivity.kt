package com.penguinpay.feature.splash.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.penguinpay.feature.splash.R
import com.penguinpay.feature.splash.databinding.ActivitySplashBinding
import com.penguinpay.navigation.HomeNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


@SuppressLint("CustomSplashScreen")
internal class SplashActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }

    private val homeNavigation: HomeNavigation by inject()

    private val viewModel: SplashViewModel by viewModel()

    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
        setupViewModel()

        viewModel.onCreate()
    }

    private fun setupViews() {
        with(binding) {
            AnimationUtils.loadAnimation(this@SplashActivity, R.anim.respiration_lamp).let {
                image.startAnimation(it)
            }
        }
    }

    private fun setupViewModel() {
        viewModel.action.observe(this, { it?.let { handleAction(it) } })
    }

    private fun handleAction(action: SplashViewModel.SplashViewAction) {
        when (action) {
            SplashViewModel.SplashViewAction.GoToHome -> handleGoToHome()
            SplashViewModel.SplashViewAction.SetDefaultNightModeAlwaysDark -> handleSetDefaultNightModeAlwaysDark()
            SplashViewModel.SplashViewAction.SetDefaultNightModeAlwaysLight -> handleSetDefaultNightModeAlwaysLight()
            SplashViewModel.SplashViewAction.SetDefaultNightModeFollowSystem -> handleSetDefaultNightModeFollowSystem()
        }
    }

    private fun handleGoToHome() {
        startActivity(homeNavigation.getHomeIntent(this@SplashActivity))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    private fun handleSetDefaultNightModeAlwaysDark() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun handleSetDefaultNightModeAlwaysLight() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun handleSetDefaultNightModeFollowSystem() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}
