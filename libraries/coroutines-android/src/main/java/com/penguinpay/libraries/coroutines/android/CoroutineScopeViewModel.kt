package com.penguinpay.libraries.coroutines.android

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class CoroutineScopeViewModel(job: Job, private val coroutineService: CoroutineService) : CoroutineScope {

    override val coroutineContext: CoroutineContext = coroutineService.Main + job

    override fun toString(): String = "CoroutineScopeViewModel(coroutineContext=$coroutineContext)"

    fun launchIdling(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        coroutineService.incrementIdlingResources()

        return this.launch(context, start, block).apply {
            invokeOnCompletion {
                coroutineService.decrementIdlingResources()
            }
        }
    }
}