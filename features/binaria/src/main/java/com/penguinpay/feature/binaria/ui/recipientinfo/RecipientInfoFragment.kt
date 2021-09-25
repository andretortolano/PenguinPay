package com.penguinpay.feature.binaria.ui.recipientinfo

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.penguinpay.common.extensions.textChanges
import com.penguinpay.feature.binaria.R
import com.penguinpay.feature.binaria.databinding.FragmentRecipientInfoBinding
import com.penguinpay.feature.binaria.ui.BinariaViewModel
import com.penguinpay.feature.binaria.ui.recipientinfo.validator.FormFieldState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class RecipientInfoFragment : Fragment() {

    companion object {
        private const val EDITTEXT_DEBOUNCE = 500L
    }

    private var _binding: FragmentRecipientInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecipientInfoViewModel by viewModel()
    private val sharedViewModel: BinariaViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRecipientInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupViewModel()

        if (savedInstanceState == null) {
            sharedViewModel.country?.let { viewModel.onViewCreated(it) }
        }
    }

    private fun setupView() {
        sharedViewModel.country?.let {
            with(binding) {
                phoneEditText.filters += InputFilter.LengthFilter(it.phoneValidSize)
                phoneLayout.prefixText = getString(R.string.recipient_info_phone_prefix, it.phonePrefix)
                phoneLayout.hint = getString(R.string.recipient_info_phone_hint, it.countryAcronym)

                firstNameEditText.textChanges(EDITTEXT_DEBOUNCE)
                    .onEach(viewModel::onFirstNameChanged)
                    .launchIn(lifecycleScope)

                lastNameEditText.textChanges(EDITTEXT_DEBOUNCE)
                    .onEach(viewModel::onLastNameChanged)
                    .launchIn(lifecycleScope)

                phoneEditText.textChanges(EDITTEXT_DEBOUNCE)
                    .onEach(viewModel::onPhoneChanged)
                    .launchIn(lifecycleScope)

                continueButton.setOnClickListener { viewModel.onContinueButtonClick() }
            }
        }
    }

    private fun setupViewModel() {
        viewModel.state.observe(viewLifecycleOwner, { it?.let { renderState(it) } })
        viewModel.action.observe(viewLifecycleOwner, { it?.let { handleAction(it) } })
    }

    private fun renderState(state: RecipientInfoViewModel.RecipientInfoViewState) {
        with(binding) {
            continueButton.isEnabled = state.isFormValid

            when (state.firstNameField) {
                is FormFieldState.Invalid -> firstNameLayout.error = getString(R.string.recipient_info_first_name_empty_error)
                is FormFieldState.Prune -> firstNameLayout.isErrorEnabled = false
                is FormFieldState.Valid -> firstNameLayout.isErrorEnabled = false
            }

            when (state.lastNameField) {
                is FormFieldState.Invalid -> lastNameLayout.error = getString(R.string.recipient_info_last_name_empty_error)
                is FormFieldState.Prune -> lastNameLayout.isErrorEnabled = false
                is FormFieldState.Valid -> lastNameLayout.isErrorEnabled = false
            }

            when (state.phoneField) {
                is FormFieldState.Invalid -> phoneLayout.error =
                    getString(R.string.recipient_info_phone_not_valid_error, viewModel.country.phoneValidSize)
                is FormFieldState.Prune -> phoneLayout.isErrorEnabled = false
                is FormFieldState.Valid -> phoneLayout.isErrorEnabled = false
            }
        }
    }

    private fun handleAction(action: RecipientInfoViewModel.RecipientInfoViewAction) {
        when (action) {
            is RecipientInfoViewModel.RecipientInfoViewAction.GoNext ->
                sharedViewModel.onRecipientInfoFilled(
                    action.firstName,
                    action.lastName,
                    action.phone
                )
        }
    }
}