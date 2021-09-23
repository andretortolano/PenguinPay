package com.penguinpay.feature.binaria

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.penguinpay.data.remote.exchange.ExchangeRemoteSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class BinariaActivity : AppCompatActivity() {

    private val exchangeRemoteSource: ExchangeRemoteSource by inject()

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, BinariaActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.binaria_activity)

        // TODO remove
        runBlocking {
            coroutineScope {
                launch {
                    exchangeRemoteSource.getUSDExchangeRates(setOf("KES", "NGN", "TZS", "UGX")).forEach {
                        Log.i("Binaria", it.toString())
                    }
                }
            }
        }
    }
}