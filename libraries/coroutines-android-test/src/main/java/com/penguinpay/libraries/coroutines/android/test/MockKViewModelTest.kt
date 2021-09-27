package com.penguinpay.libraries.coroutines.android.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Subject
import com.google.common.truth.Truth.assertThat
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel
import com.penguinpay.libraries.coroutines.test.TestCoroutineService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.After
import org.junit.Before
import org.junit.Rule

@Suppress("unused")
abstract class MockKViewModelTest<VM : CoroutinesViewModel<STATE, ACTION>, STATE, ACTION> {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Deprecated("use extension functions")
    protected val states: ArrayList<STATE> = arrayListOf()

    private val stateObserver: Observer<STATE> = Observer { it?.let { states.add(it) } }

    @Deprecated("use extension functions")
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

    fun MockKViewModelTest<VM, STATE, ACTION>.given(block: () -> Unit): ViewModelGivenBlock<VM> {
        return with(ViewModelGivenBlock(viewModel)) {
            block()
            return@with this
        }
    }

    infix fun MockKViewModelTest<VM, STATE, ACTION>.whenViewModel(block: VM.() -> Unit): ViewModelWhenBlock<VM, STATE, ACTION> {
        return ViewModelWhenBlock(viewModel).let { whenBlock ->
            with(viewModel) {
                block()
                whenBlock
            }
        }
    }

    class ViewModelGivenBlock<VM>(private val viewModel: VM)

    infix fun ViewModelGivenBlock<VM>.whenViewModel(block: VM.() -> Unit): ViewModelWhenBlock<VM, STATE, ACTION> {
        return ViewModelWhenBlock(viewModel).let { whenBlock ->
            with(viewModel) {
                block()
                whenBlock
            }
        }
    }

    class ViewModelWhenBlock<VM : CoroutinesViewModel<STATE, ACTION>, STATE, ACTION>(viewModel: VM) {
        val states: ArrayList<STATE> = arrayListOf()

        val actions: ArrayList<ACTION> = arrayListOf()

        private val stateObserver: Observer<STATE> = Observer { it?.let { states.add(it) } }

        private val actionObserver: Observer<ACTION> = Observer { it?.let { actions.add(it) } }

        init {
            viewModel.state.observeForever(stateObserver)
            viewModel.action.observeForever(actionObserver)
        }
    }

    infix fun ViewModelWhenBlock<VM, STATE, ACTION>.then(block: ViewModelThenBlock<STATE, ACTION>.() -> Unit): Unit {
        return ViewModelThenBlock(states, actions).withBlock(block)
    }

    class ViewModelThenBlock<STATE, ACTION>(private val states: ArrayList<STATE>, private val actions: ArrayList<ACTION>) {

        private var isStateSizeVerified = false
        private var isActionSizeVerified = false

        fun withBlock(block: ViewModelThenBlock<STATE, ACTION>.() -> Unit) {
            block()

            if (isStateSizeVerified.not()) {
                assertStatesIsEmpty()
            }

            if (isActionSizeVerified.not()) {
                assertActionsIsEmpty()
            }
        }

        /**
         * assert states size is empty counting out the initial state
         */
        private fun assertStatesIsEmpty() {
            assertThat(states.size - 1).isEqualTo(0)
        }

        /**
         * assert actions size is empty
         */
        private fun assertActionsIsEmpty() {
            assertThat(actions).isEmpty()
        }

        /**
         * assert states size counting out the initial state
         */
        fun ViewModelThenBlock<STATE, ACTION>.assertStatesSize(expected: Int) {
            assertThat(states.size - 1).isEqualTo(expected)
            isStateSizeVerified = true
        }

        /**
         * assert states size
         */
        fun ViewModelThenBlock<STATE, ACTION>.assertActionsSize(expected: Int) {
            assertThat(actions.size).isEqualTo(expected)
            isActionSizeVerified = true
        }

        /**
         * with last state if not the initial state
         */
        fun <R> ViewModelThenBlock<STATE, ACTION>.withState(position: Int = states.lastIndex, block: STATE.() -> R): R {
            assertThat(states.size - 1).isGreaterThan(0)
            isStateSizeVerified = true
            return with(states[position], block)
        }

        /**
         * with last action
         */
        fun <R> ViewModelThenBlock<STATE, ACTION>.withAction(position: Int = actions.lastIndex, block: ACTION.() -> R): R {
            assertThat(states.size).isGreaterThan(0)
            isActionSizeVerified = true
            return with(actions[position], block)
        }

    }

    /**
     * assert states size counting out the first set on ViewModel init
     */
    @Deprecated("")
    fun MockKViewModelTest<VM, STATE, ACTION>.assertStatesSize(expected: Int) {
        assertThat(states.size - 1).isEqualTo(expected)
    }


    /**
     * assert state for position counting out the first set on ViewModel init
     */
    fun MockKViewModelTest<VM, STATE, ACTION>.assertState(position: Int): Subject {
        return assertThat(states[position + 1])
    }

    /**
     * get state for position counting out the set on ViewModel init
     */
    fun MockKViewModelTest<VM, STATE, ACTION>.getState(position: Int): STATE {
        return states[position + 1]
    }

    /**
     * with last State
     */
    fun <R> MockKViewModelTest<VM, STATE, ACTION>.withState(position: Int, block: STATE.() -> R): R {
        return with(getState(position), block)
    }

    /**
     * get last State
     */
    @Deprecated(" ")
    fun MockKViewModelTest<VM, STATE, ACTION>.getLastState(): STATE {
        assertThat(states).isNotEmpty()
        return states.last()
    }

    /**
     * with last State
     */
    @Deprecated("remover")
    fun <R> MockKViewModelTest<VM, STATE, ACTION>.withLastState(block: STATE.() -> R): R {
        return with(getLastState(), block)
    }

    /**
     * assert Action size
     */
    @Deprecated(" ")
    fun MockKViewModelTest<VM, STATE, ACTION>.assertActionsSize(expected: Int) {
        assertThat(actions.size).isEqualTo(expected)
    }

    /**
     * assert that no Action was sent
     */
    @Deprecated("remover")
    fun MockKViewModelTest<VM, STATE, ACTION>.assertNoAction() {
        assertThat(actions.size).isEqualTo(0)
    }

    /**
     * get action for position
     */
    fun MockKViewModelTest<VM, STATE, ACTION>.getAction(position: Int): ACTION {
        return actions[position]
    }

    /**
     * with last State
     */
    fun <R> MockKViewModelTest<VM, STATE, ACTION>.withAction(position: Int, block: ACTION.() -> R): R {
        return with(getAction(position), block)
    }

    /**
     * get last State
     */
    @Deprecated("")
    fun MockKViewModelTest<VM, STATE, ACTION>.getLastAction(): ACTION {
        assertThat(actions).isNotEmpty()
        return actions.last()
    }

    /**
     * with last action
     */
    @Deprecated("todo")
    fun <R> MockKViewModelTest<VM, STATE, ACTION>.withLastAction(block: ACTION.() -> R): R {
        return with(getLastAction(), block)
    }

    /**
     * clear all actions and older states to make it easier for more complex tests
     */
    fun MockKViewModelTest<VM, STATE, ACTION>.clearViewModelHistory() {
        states.removeAll(states.filter { it != states.last() })
        actions.clear()
    }
}