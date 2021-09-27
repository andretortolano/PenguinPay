package com.penguinpay.libraries.coroutines.android.test.block

import com.google.common.truth.Truth.assertThat
import kotlin.reflect.KClass

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ViewStateThenBlock<STATE : Any>(private val states: ArrayList<STATE>, private val sizeAssertionCallback: () -> Unit) {

    fun ViewStateThenBlock<STATE>.hasSize(expected: Int) {
        sizeAssertionCallback()
        assertThat(states).hasSize(expected)
    }

    fun ViewStateThenBlock<STATE>.isEmpty() {
        sizeAssertionCallback()
        assertThat(states).isEmpty()
    }

    fun ViewStateThenBlock<STATE>.get(position: Int, block: (STATE.() -> Unit)? = null): STATE {
        assertThat(states.size).isGreaterThan(position)
        sizeAssertionCallback()
        return states[position].apply {
            block?.let {
                with(this, it)
            }
        }
    }

    fun ViewStateThenBlock<STATE>.getLast(block: (STATE.() -> Unit)? = null): STATE {
        assertThat(states).isNotEmpty()
        sizeAssertionCallback()
        return states.last().apply {
            block?.let {
                with(this, it)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    infix fun <T : STATE> STATE.isA(type: KClass<T>): T {
        assertThat(this).isInstanceOf(type.java)
        return this as T
    }

    infix fun <T : STATE> T.and(block: T.() -> Unit) {
        return with(this, block)
    }
}