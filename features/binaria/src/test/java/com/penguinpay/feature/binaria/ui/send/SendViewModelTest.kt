package com.penguinpay.feature.binaria.ui.send

import com.penguinpay.feature.binaria.ui.send.SendViewModel.SendViewAction
import com.penguinpay.feature.binaria.ui.send.SendViewModel.SendViewState
import com.penguinpay.libraries.coroutines.android.test.MockKViewModelTest
import io.mockk.impl.annotations.MockK

internal class SendViewModelTest : MockKViewModelTest<SendViewModel, SendViewState, SendViewAction>() {

    @MockK
    private lateinit var model: SendModel

    // TODO

}