package com.penguinpay.lib.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultCoroutineService : CoroutineService {
    override val Main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val IO: CoroutineDispatcher
        get() = Dispatchers.IO

    override fun incrementIdlingResources() {
        // Nothing
    }

    override fun decrementIdlingResources() {
        // Nothing
    }
}