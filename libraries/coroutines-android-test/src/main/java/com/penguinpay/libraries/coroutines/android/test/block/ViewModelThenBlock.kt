package com.penguinpay.libraries.coroutines.android.test.block

import com.google.common.truth.Truth.assertThat

@Suppress("unused")
class ViewModelThenBlock<STATE, ACTION>(private val states: ArrayList<STATE>, private val actions: ArrayList<ACTION>) {

    private var isStateSizeVerified = false
    private var isActionSizeVerified = false

    fun withBlock(block: ViewModelThenBlock<STATE, ACTION>.() -> Unit) {
        block()

        if (isStateSizeVerified.not()) {
            assertStatesIsEmpty()
        }

        if (isActionSizeVerified.not()) {
            assertActionsIsEmpty()
        }
    }

    private fun assertStatesIsEmpty() {
        assertThat(states).isEmpty()
    }

    /**
     * assert actions size is empty
     */
    private fun assertActionsIsEmpty() {
        assertThat(actions).isEmpty()
    }

    /**
     * assert states size counting out the initial state
     */
    fun ViewModelThenBlock<STATE, ACTION>.assertStatesSize(expected: Int) {
        assertThat(states.size).isEqualTo(expected)
        isStateSizeVerified = true
    }

    /**
     * assert states size
     */
    fun ViewModelThenBlock<STATE, ACTION>.assertActionsSize(expected: Int) {
        assertThat(actions.size).isEqualTo(expected)
        isActionSizeVerified = true
    }

    /**
     * with state
     */
    fun <R> ViewModelThenBlock<STATE, ACTION>.withState(position: Int, block: STATE.() -> R): R {
        return with(states[position], block)
    }

    /**
     * with last state if not the initial state
     */
    fun <R> ViewModelThenBlock<STATE, ACTION>.withLastState(size: Int, block: STATE.() -> R): R {
        assertStatesSize(size)
        return with(states.last(), block)
    }

    /**
     * with action
     */
    fun <R> ViewModelThenBlock<STATE, ACTION>.withAction(position: Int, block: ACTION.() -> R): R {
        return with(actions[position], block)
    }

    /**
     * with last action
     */
    fun <R> ViewModelThenBlock<STATE, ACTION>.withLastAction(size: Int, block: ACTION.() -> R): R {
        assertActionsSize(size)
        return with(actions.last(), block)
    }
}