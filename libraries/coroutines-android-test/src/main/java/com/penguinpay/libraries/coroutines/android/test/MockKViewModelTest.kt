package com.penguinpay.libraries.coroutines.android.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel
import com.penguinpay.libraries.coroutines.android.test.block.ViewModelGivenBlock
import com.penguinpay.libraries.coroutines.android.test.block.ViewModelThenBlock
import com.penguinpay.libraries.coroutines.android.test.block.ViewModelWhenBlock
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.After
import org.junit.Before
import org.junit.Rule

@Suppress("unused")
abstract class MockKViewModelTest<VM : CoroutinesViewModel<STATE, ACTION>, STATE : Any, ACTION : Any> {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Suppress("MemberVisibilityCanBePrivate")
    protected val dispatching = TestCoroutineService()

    @InjectMockKs
    protected lateinit var viewModel: VM

    @Before
    fun setup() {
        MockKAnnotations.init(this)

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

    fun MockKViewModelTest<VM, STATE, ACTION>.withInitState(block: STATE.() -> Unit) {
        viewModel.state.observeForever(block)
    }

    fun MockKViewModelTest<VM, STATE, ACTION>.given(block: () -> Unit): ViewModelGivenBlock {
        return ViewModelGivenBlock().let {
            block()
            it
        }
    }

    fun MockKViewModelTest<VM, STATE, ACTION>.whenViewModel(block: VM.() -> Unit): ViewModelWhenBlock<VM, STATE, ACTION> {
        return ViewModelWhenBlock(viewModel).let { whenBlock ->
            viewModel.block()
            whenBlock
        }
    }

    infix fun ViewModelGivenBlock.whenViewModel(block: VM.() -> Unit): ViewModelWhenBlock<VM, STATE, ACTION> {
        return ViewModelWhenBlock(viewModel).let { whenBlock ->
            viewModel.block()
            whenBlock
        }
    }

    infix fun ViewModelWhenBlock<VM, STATE, ACTION>.then(block: ViewModelThenBlock<STATE, ACTION>.() -> Unit) {
        return ViewModelThenBlock(states, actions).withBlock(block)
    }
}