package com.penguinpay.libraries.coroutines.android.test

import io.mockk.MockKAnnotations
import org.junit.After
import org.junit.Before

abstract class MockKCoroutinesTest {

    protected val dispatching = TestCoroutineService()

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
}