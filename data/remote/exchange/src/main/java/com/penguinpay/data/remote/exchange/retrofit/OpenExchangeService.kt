package com.penguinpay.data.remote.exchange.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenExchangeService {

    @GET("latest.json")
    suspend fun getLatestUSDExchanges(@Query("symbols") symbols: String?): OpenExchangeResponse

    @GET("latest.json")
    suspend fun getLatestExchanges(@Query("base") base: String, @Query("symbols") symbols: String?): OpenExchangeResponse
}