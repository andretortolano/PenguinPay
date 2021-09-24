package com.penguinpay.feature.binaria.ui

import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewAction
import com.penguinpay.feature.binaria.ui.BinariaViewModel.BinariaViewState
import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class BinariaViewModel(
    coroutineService: CoroutineService,
) : CoroutinesViewModel<BinariaViewState, BinariaViewAction>(coroutineService) {

    sealed class BinariaViewState {
        object Loading : BinariaViewState()
    }

    sealed class BinariaViewAction {
        object Nothing : BinariaViewAction()
    }

    fun onCreate() {

    }
}