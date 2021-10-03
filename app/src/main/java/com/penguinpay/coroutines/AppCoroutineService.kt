package com.penguinpay.coroutines

import com.penguinpay.libraries.coroutines.android.CoroutineService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class AppCoroutineService : CoroutineService {
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