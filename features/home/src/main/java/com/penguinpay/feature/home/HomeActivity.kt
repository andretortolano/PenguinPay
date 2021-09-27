package com.penguinpay.feature.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.penguinpay.feature.home.databinding.HomeActivityBinding
import com.penguinpay.navigation.BinariaNavigation
import org.koin.android.ext.android.inject

internal class HomeActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    private val binariaNavigation: BinariaNavigation by inject()

    private val binding by lazy { HomeActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goToBinaria.setOnClickListener {
            startActivity(binariaNavigation.getBinariaIntent(this))
        }
    }
}