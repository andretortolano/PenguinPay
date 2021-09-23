package com.penguinpay.lib.coroutines.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.penguinpay.lib.coroutines.CoroutineService
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

@Suppress("PropertyName")
abstract class CoroutinesViewModel<STATE, ACTION>(coroutineService: CoroutineService) : ViewModel() {
    @Suppress("MemberVisibilityCanBePrivate")
    protected val _state: MutableLiveData<STATE> = MutableLiveData<STATE>()

    val state: LiveData<STATE> get() = _state

    @Suppress("MemberVisibilityCanBePrivate")
    protected val _action: MutableLiveData<ACTION> = ActionLiveData<ACTION>()

    val action: LiveData<ACTION> get() = _action

    private val supervisorJob = SupervisorJob()

    protected val scope = CoroutineScopeViewModel(supervisorJob, coroutineService)

    override fun onCleared() {
        super.onCleared()
        supervisorJob.cancelChildren()
    }
}