package com.penguinpay.feature.binaria.gateway

import com.google.common.truth.Truth.assertThat
import com.penguinpay.data.remote.exchange.ExchangeRemoteSource
import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway
import io.mockk.mockk
import org.junit.Test

class BinariaExchangeRateGatewayTest {

    private val remoteSource = mockk<ExchangeRemoteSource>()

    private val gateway = BinariaExchangeRateGateway(remoteSource)

    @Test
    fun `gateway SHOULD extend ExchangeRateGateway`() {
        assertThat(gateway).isInstanceOf(ExchangeRateGateway::class.java)
    }

}