package com.penguinpay.libraries.coroutines.android.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel
import com.penguinpay.libraries.coroutines.test.TestCoroutineService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class MockKViewModelTest<VM : CoroutinesViewModel<STATE, ACTION>, STATE, ACTION> {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    protected val states: ArrayList<STATE> = arrayListOf()

    private val stateObserver: Observer<STATE> = Observer { it?.let { states.add(it) } }

    protected val actions: ArrayList<ACTION> = arrayListOf()

    private val eventObserver: Observer<ACTION> = Observer { it?.let { actions.add(it) } }

    protected val dispatching = TestCoroutineService()

    @InjectMockKs
    protected lateinit var viewModel: VM

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel.state.observeForever(stateObserver)
        viewModel.action.observeForever(eventObserver)

        beforeTest()
    }

    @After
    fun tearDown() {
        afterTest()
        dispatching.clean()
    }

    open fun beforeTest() {
        // Override when needed
    }

    open fun afterTest() {
        // Override when needed
    }
}