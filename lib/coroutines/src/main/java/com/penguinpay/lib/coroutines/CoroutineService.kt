package com.penguinpay.lib.coroutines

import kotlinx.coroutines.CoroutineDispatcher

@Suppress("PropertyName")
interface CoroutineService {
    val Main: CoroutineDispatcher
    val IO: CoroutineDispatcher

    fun incrementIdlingResources()
    fun decrementIdlingResources()
}