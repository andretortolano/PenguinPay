package com.penguinpay.feature.binaria.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.penguinpay.feature.binaria.R
import com.penguinpay.feature.binaria.databinding.BinariaActivityBinding
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction.NavigateToReceipt
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction.NavigateToRecipientInfo
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction.NavigateToSendRecipient
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class BinariaActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, BinariaActivity::class.java)
        }
    }

    private val binding by lazy { BinariaActivityBinding.inflate(layoutInflater) }

    private val viewModel: BinariaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.action.observe(this, { it?.let { handleAction(it) } })
    }

    private fun handleAction(action: BinariaViewModel.BinariaViewAction) {
        with(findNavController(R.id.nav_host_fragment)) {
            when (action) {
                NavigateToReceipt -> navigate(R.id.action_sendRecipientFragment_to_receiptFragment)
                NavigateToRecipientInfo -> navigate(R.id.action_countrySelectionFragment_to_recipientInfoFragment)
                NavigateToSendRecipient -> navigate(R.id.action_recipientInfoFragment_to_sendRecipientFragment)
            }
        }
    }
}