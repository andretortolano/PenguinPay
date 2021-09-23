package com.penguinpay.domain.exchange.interactor

import com.google.common.truth.Truth.*
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.domain.exchange.testing.MockKCoroutinesTest
import io.mockk.impl.annotations.InjectMockKs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetExchangeCountriesUseCaseTest: MockKCoroutinesTest() {

    @InjectMockKs
    private lateinit var useCase: GetExchangeCountriesUseCase

    @Test
    fun `UseCase SHOULD return this exact list`() = runBlockingTest {
        // When
        val result = useCase()
        // Then
        with(result) {
            assertThat(size).isEqualTo(4)
            assertThat(get(0)).isEqualTo(ExchangeCountryEntity("Kenya", "KES", "+254", 9))
            assertThat(get(1)).isEqualTo(ExchangeCountryEntity("Nigeria", "NGN", "+234", 7))
            assertThat(get(2)).isEqualTo(ExchangeCountryEntity("Tanzania", "TZS", "+255", 9))
            assertThat(get(3)).isEqualTo(ExchangeCountryEntity("Uganda", "UGX", "+256", 7))
        }
    }
}