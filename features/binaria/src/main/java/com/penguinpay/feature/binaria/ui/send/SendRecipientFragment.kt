package com.penguinpay.feature.binaria.ui.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.penguinpay.feature.binaria.R
import com.penguinpay.feature.binaria.databinding.FragmentSendRecipientBinding
import com.penguinpay.feature.binaria.ui.BinariaViewModel
import com.penguinpay.libraries.extensions.android.textChanges
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SendRecipientFragment : Fragment() {

    private var _binding: FragmentSendRecipientBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SendRecipientViewModel by viewModel()
    private val sharedViewModel: BinariaViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSendRecipientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        with(binding) {
            usdBinaruEditText.textChanges()
                .onEach { viewModel.onUSDBinaryChanged(it, sharedViewModel.country!!.countryAcronym) }
                .launchIn(lifecycleScope)

            sendButton.setOnClickListener {
                viewModel.onSendButtonClick(
                    sharedViewModel.completeName,
                    sharedViewModel.completePhone,
                    sharedViewModel.country!!.countryAcronym
                )
            }
        }
    }

    private fun setupViewModel() {
        viewModel.state.observe(viewLifecycleOwner, { it?.let { renderState(it) } })
        viewModel.action.observe(viewLifecycleOwner, { it?.let { handleAction(it) } })
    }

    private fun renderState(state: SendRecipientViewModel.SendRecipientViewState) {
        with(binding) {
            loadingExchange.isVisible = state.isLoadingExchangeValue
            sendDescription.isVisible = state.isLoadingExchangeValue.not() && state.isFormValid

            sendLoading.isVisible = state.isSending
            sendButton.isVisible = state.isSending.not()

            sendDescription.text = getString(
                R.string.send_recipient_send_description_text,
                sharedViewModel.completeName,
                sharedViewModel.country?.countryName,
                state.recipientBinary,
                sharedViewModel.country?.countryAcronym
            )
        }
    }

    private fun handleAction(action: SendRecipientViewModel.SendRecipientViewAction) {
        when (action) {
            is SendRecipientViewModel.SendRecipientViewAction.SendTransfer -> sharedViewModel.onTransactionSent(action.receipt)
            SendRecipientViewModel.SendRecipientViewAction.NumberNotBinaryError -> sendToast(R.string.send_recipient_not_binary_error)
            SendRecipientViewModel.SendRecipientViewAction.SomethingWentWrong -> sendToast(R.string.send_recipient_something_went_wrong)
        }
    }

    private fun sendToast(res: Int) {
        Toast.makeText(activity, res, Toast.LENGTH_SHORT).show()
    }
}