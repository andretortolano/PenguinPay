package com.penguinpay.libraries.coroutines.android

import kotlinx.coroutines.CoroutineDispatcher

@Suppress("PropertyName")
interface CoroutineService {
    val Main: CoroutineDispatcher
    val IO: CoroutineDispatcher

    fun incrementIdlingResources()
    fun decrementIdlingResources()
}