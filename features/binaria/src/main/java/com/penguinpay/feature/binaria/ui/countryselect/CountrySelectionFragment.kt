package com.penguinpay.feature.binaria.ui.countryselect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.feature.binaria.databinding.FragmentCountrySelectionBinding
import com.penguinpay.feature.binaria.ui.BinariaViewModel
import com.penguinpay.feature.binaria.ui.countryselect.adapter.CountryAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountrySelectionFragment : Fragment() {

    private var _binding: FragmentCountrySelectionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountrySelectionViewModel by viewModel()
    private val sharedViewModel: BinariaViewModel by sharedViewModel()

    private val countryAdapter = CountryAdapter(object : CountryAdapter.OnCountryAdapterListener {
        override fun onItemCountryClick(country: ExchangeCountryEntity) {
            sharedViewModel.onCountrySelected(country)
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCountrySelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupViewModel()

        if (savedInstanceState == null) {
            viewModel.onStart()
        }
    }

    private fun setupViewModel() {
        viewModel.state.observe(viewLifecycleOwner, { it?.let { renderState(it) } })
        viewModel.action.observe(viewLifecycleOwner, { it?.let { handleAction(it) } })
    }

    private fun renderState(state: CountrySelectionViewModel.CountrySelectionViewState) {
        when (state) {
            CountrySelectionViewModel.CountrySelectionViewState.Loading -> renderLoading()
            is CountrySelectionViewModel.CountrySelectionViewState.Success -> renderSuccess(state.countryList)
        }
    }

    private fun renderLoading() {
        binding.loading.isVisible = true
        binding.recyclerview.isVisible = false
    }

    private fun renderSuccess(countryList: List<ExchangeCountryEntity>) {
        countryAdapter.update(countryList)

        binding.loading.isVisible = false
        binding.recyclerview.isVisible = true
    }

    private fun handleAction(action: CountrySelectionViewModel.CountrySelectionViewAction) {
        when (action) {
            CountrySelectionViewModel.CountrySelectionViewAction.Nothing -> TODO()
        }
    }

    private fun setupView() {
        binding.recyclerview.adapter = countryAdapter
    }
}