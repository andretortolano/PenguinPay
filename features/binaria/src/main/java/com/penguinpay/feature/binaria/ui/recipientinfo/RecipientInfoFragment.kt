package com.penguinpay.feature.binaria.ui.recipientinfo

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
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
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.skip
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class RecipientInfoFragment : Fragment() {

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
            sharedViewModel.country?.let { viewModel.onStart(it) }
        }
    }

    private fun setupView() {
        sharedViewModel.country?.let {
            with(binding) {
                phoneEditText.filters += InputFilter.LengthFilter(it.phoneValidSize)
                phoneLayout.prefixText = getString(R.string.recipient_info_phone_prefix, it.phonePrefix)
                phoneLayout.hint = getString(R.string.recipient_info_phone_hint, it.countryAcronym)

                firstNameEditText.textChanges()
                    .debounce(300)
                    .map { it.toString() }
                    .onEach(viewModel::onFirstNameChanged)
                    .launchIn(lifecycleScope)

                lastNameEditText.textChanges()
                    .debounce(300)
                    .map { it.toString() }
                    .onEach(viewModel::onLastNameChanged)
                    .launchIn(lifecycleScope)

                phoneEditText.textChanges()
                    .debounce(300)
                    .map { it.toString() }
                    .onEach(viewModel::onPhoneChanged)
                    .launchIn(lifecycleScope)
            }
        }
    }

    private fun setupViewModel() {
        viewModel.state.observe(viewLifecycleOwner, { it?.let { renderState(it) } })
        viewModel.action.observe(viewLifecycleOwner, { it?.let { handleAction(it) } })
    }

    private fun renderState(state: RecipientInfoViewModel.RecipientInfoViewState) {
        Log.i("renderState", state.toString())
        when (state.firstNameField) {
            is FormFieldState.Invalid -> binding.firstNameLayout.error = getString(R.string.recipient_info_first_name_empty_error)
            is FormFieldState.Prune -> binding.firstNameLayout.isErrorEnabled = false
            is FormFieldState.Valid -> binding.firstNameLayout.isErrorEnabled = false
        }

        when (state.lastNameField) {
            is FormFieldState.Invalid -> binding.lastNameLayout.error = getString(R.string.recipient_info_last_name_empty_error)
            is FormFieldState.Prune -> binding.lastNameLayout.isErrorEnabled = false
            is FormFieldState.Valid -> binding.lastNameLayout.isErrorEnabled = false
        }

        when (state.phoneField) {
            is FormFieldState.Invalid -> binding.phoneLayout.error =
                getString(R.string.recipient_info_phone_not_valid_error, viewModel.country.phoneValidSize)
            is FormFieldState.Prune -> binding.phoneLayout.isErrorEnabled = false
            is FormFieldState.Valid -> binding.phoneLayout.isErrorEnabled = false
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