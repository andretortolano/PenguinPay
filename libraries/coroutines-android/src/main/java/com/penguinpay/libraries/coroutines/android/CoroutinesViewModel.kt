package com.penguinpay.libraries.coroutines.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

@Suppress("PropertyName")
abstract class CoroutinesViewModel<STATE, ACTION>(coroutineService: CoroutineService, private val initState: STATE) : ViewModel() {
    @Suppress("MemberVisibilityCanBePrivate")
    protected val _state: MutableLiveData<STATE> = MutableLiveData<STATE>()

    val state: LiveData<STATE> get() = _state

    val stateValue: STATE get() = state.value ?: initState

    @Suppress("MemberVisibilityCanBePrivate")
    protected val _action: MutableLiveData<ACTION> = ActionLiveData<ACTION>()

    val action: LiveData<ACTION> get() = _action

    private val supervisorJob = SupervisorJob()

    protected val scope = CoroutineScopeViewModel(supervisorJob, coroutineService)

    init {
        _state.value = initState
    }

    override fun onCleared() {
        super.onCleared()
        supervisorJob.cancelChildren()
    }
}