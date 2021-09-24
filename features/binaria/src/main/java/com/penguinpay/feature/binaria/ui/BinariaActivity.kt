package com.penguinpay.feature.binaria.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.penguinpay.feature.binaria.databinding.BinariaActivityBinding

internal class BinariaActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, BinariaActivity::class.java)
        }
    }

    private val binding by lazy { BinariaActivityBinding.inflate(layoutInflater) }

    //private val viewModel: BinariaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}