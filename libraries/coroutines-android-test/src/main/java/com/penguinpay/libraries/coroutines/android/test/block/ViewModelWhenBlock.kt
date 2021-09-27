package com.penguinpay.libraries.coroutines.android.test.block

import androidx.lifecycle.Observer
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

class ViewModelWhenBlock<VM : CoroutinesViewModel<STATE, ACTION>, STATE, ACTION>(viewModel: VM) {
    internal val states: ArrayList<STATE> = arrayListOf()

    internal val actions: ArrayList<ACTION> = arrayListOf()

    private val stateObserver: Observer<STATE> = Observer { it?.let { states.add(it) } }

    private val actionObserver: Observer<ACTION> = Observer { it?.let { actions.add(it) } }

    init {
        viewModel.state.observeForever(stateObserver)
        viewModel.action.observeForever(actionObserver)

        states.clear()
        actions.clear()
    }
}