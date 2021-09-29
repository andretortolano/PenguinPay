package com.penguinpay.domain.exchange

import com.google.common.truth.Truth.assertThat
import com.penguinpay.domain.exchange.GetExchangeCountriesUseCase
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.libraries.coroutines.test.MockKCoroutinesTest
import io.mockk.impl.annotations.InjectMockKs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetExchangeCountriesUseCaseTest : MockKCoroutinesTest() {

    @InjectMockKs
    private lateinit var useCase: GetExchangeCountriesUseCase

    @Test
    fun `UseCase SHOULD return this exact list`() = runBlockingTest {
        // When
        val result = useCase()
        // Then
        with(result.countries) {
            assertThat(size).isEqualTo(4)
            assertThat(get(0)).isEqualTo(ExchangeCountryEntity("Kenya", "KES", "+254", 9))
            assertThat(get(1)).isEqualTo(ExchangeCountryEntity("Nigeria", "NGN", "+234", 7))
            assertThat(get(2)).isEqualTo(ExchangeCountryEntity("Tanzania", "TZS", "+255", 9))
            assertThat(get(3)).isEqualTo(ExchangeCountryEntity("Uganda", "UGX", "+256", 7))
        }
    }
}