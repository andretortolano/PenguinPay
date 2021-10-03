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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


@SuppressLint("CustomSplashScreen")
internal class SplashActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }

    private val homeNavigation: HomeNavigation by inject()

    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()

        // TODO replace this for viewModel
        GlobalScope.launch {
            delay(2000)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

            startActivity(homeNavigation.getHomeIntent(this@SplashActivity))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }

    private fun setupViews() {
        with(binding) {
            AnimationUtils.loadAnimation(this@SplashActivity, R.anim.respiration_lamp).let {
                image.startAnimation(it)
            }
        }
    }
}