package com.penguinpay.libraries.coroutines.android.test.block

import com.google.common.truth.Truth.assertThat

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ViewModelThenBlock<STATE : Any, ACTION : Any>(private val states: ArrayList<STATE>, private val actions: ArrayList<ACTION>) {

    private var isStateSizeVerified = false
    private var isActionSizeVerified = false

    internal fun withBlock(block: ViewModelThenBlock<STATE, ACTION>.() -> Unit) {
        block()

        if (isStateSizeVerified.not()) {
            assertThat(states).isEmpty()
        }

        if (isActionSizeVerified.not()) {
            assertThat(actions).isEmpty()
        }
    }

    infix fun ViewModelThenBlock<STATE, ACTION>.onStates(block: ViewStateThenBlock<STATE>.() -> Unit): ViewModelThenBlock<STATE, ACTION> {
        return with(ViewStateThenBlock(states) { isStateSizeVerified = true }) {
            block()
            return@with this@ViewModelThenBlock
        }
    }

    infix fun ViewModelThenBlock<STATE, ACTION>.onActions(block: ViewActionThenBlock<ACTION>.() -> Unit): ViewModelThenBlock<STATE, ACTION> {
        return with(ViewActionThenBlock(actions) { isActionSizeVerified = true }) {
            block()
            return@with this@ViewModelThenBlock
        }
    }
}