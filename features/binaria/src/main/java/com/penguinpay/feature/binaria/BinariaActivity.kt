package com.penguinpay.feature.binaria

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BinariaActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, BinariaActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.binaria_activity)
    }
}