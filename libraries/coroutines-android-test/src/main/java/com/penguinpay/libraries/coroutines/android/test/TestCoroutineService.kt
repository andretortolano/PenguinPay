package com.penguinpay.libraries.coroutines.android.test

import com.penguinpay.libraries.coroutines.android.CoroutineService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

@Suppress("EXPERIMENTAL_API_USAGE")
class TestCoroutineService : CoroutineService {

    private val dispatcher = TestCoroutineDispatcher()

    override val Main: CoroutineDispatcher
        get() = dispatcher
    override val IO: CoroutineDispatcher
        get() = dispatcher

    override fun incrementIdlingResources() {

    }

    override fun decrementIdlingResources() {

    }

    fun clean() {
        dispatcher.cleanupTestCoroutines()
    }
}