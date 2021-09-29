package com.penguinpay.feature.binaria.ui.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.penguinpay.feature.binaria.R
import com.penguinpay.feature.binaria.databinding.FragmentReceiptBinding
import com.penguinpay.feature.binaria.ui.BinariaViewModel
import com.penguinpay.libraries.extensions.android.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ReceiptFragment : Fragment() {

    private var _binding: FragmentReceiptBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReceiptViewModel by viewModel()
    private val sharedViewModel: BinariaViewModel by sharedViewModel()

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.onBackPressed()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReceiptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        with(requireActivity()) {
            hideKeyboard()
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        }

        with(binding) {
            closeButton.setOnClickListener { viewModel.onCloseClick() }

            sharedViewModel.receipt?.let {
                recipientNameValue.text = it.recipientName
                recipientPhoneValue.text = it.recipientPhone
                recipientSentValue.text = getString(R.string.receipt_recipient_sent_value, it.amount, it.currency)
            }
        }
    }

    private fun setupViewModel() {
        viewModel.action.observe(viewLifecycleOwner, { it?.let { handleAction(it) } })
    }

    private fun handleAction(action: ReceiptViewModel.ReceiptViewAction) {
        when (action) {
            ReceiptViewModel.ReceiptViewAction.Close -> requireActivity().finish()
        }
    }
}