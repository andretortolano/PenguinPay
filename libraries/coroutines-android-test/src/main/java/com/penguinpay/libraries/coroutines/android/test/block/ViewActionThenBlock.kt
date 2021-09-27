package com.penguinpay.libraries.coroutines.android.test.block

import com.google.common.truth.Truth.assertThat
import kotlin.reflect.KClass

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ViewActionThenBlock<ACTION : Any>(private val actions: ArrayList<ACTION>, private val sizeAssertionCallback: () -> Unit) {

    fun ViewActionThenBlock<ACTION>.hasSize(expected: Int) {
        sizeAssertionCallback()
        assertThat(actions).hasSize(expected)
    }

    fun ViewActionThenBlock<ACTION>.isEmpty() {
        sizeAssertionCallback()
        assertThat(actions).isEmpty()
    }

    fun ViewActionThenBlock<ACTION>.get(position: Int, block: (ACTION.() -> Unit)? = null): ACTION {
        assertThat(actions.size).isGreaterThan(position)
        sizeAssertionCallback()
        return actions[position].apply {
            block?.let {
                with(this, it)
            }
        }
    }

    fun ViewActionThenBlock<ACTION>.getLast(block: (ACTION.() -> Unit)? = null): ACTION {
        assertThat(actions).isNotEmpty()
        sizeAssertionCallback()
        return actions.last().apply {
            block?.let {
                with(this, it)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    infix fun <T : ACTION> ACTION.isA(type: KClass<T>): T {
        assertThat(this).isInstanceOf(type.java)
        return this as T
    }

    infix fun <T : ACTION> T.and(block: T.() -> Unit) {
        return with(this, block)
    }
}