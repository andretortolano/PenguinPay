package com.penguinpay.libraries.coroutines.android.test.block

import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

class ViewModelGivenBlock<VM : CoroutinesViewModel<STATE, ACTION>, STATE, ACTION>(private val viewModel: VM)