package com.penguinpay.data.remote.exchange.di

import com.google.gson.GsonBuilder
import com.penguinpay.data.remote.exchange.BuildConfig
import com.penguinpay.data.remote.exchange.ExchangeRemoteSource
import com.penguinpay.data.remote.exchange.retrofit.OpenExchangeService
import com.penguinpay.data.remote.exchange.retrofit.RetrofitExchangeSource
import com.penguinpay.di.KoinModule
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteExchangeKoinModule : KoinModule {

    override val module: Module = module {
        single<ExchangeRemoteSource> { RetrofitExchangeSource(getRetrofit().create(OpenExchangeService::class.java)) }
    }

    private fun getRetrofit(): Retrofit {
        val url = "https://openexchangerates.org/api/"
        val gson = GsonBuilder().create()

        val okHttp = OkHttpClient.Builder().also {
            if (BuildConfig.DEBUG) {
                it.addInterceptor(getHttpLoggingInterceptor())
            }
            it.addInterceptor(getDefaultParamInterceptor())
        }.build()

        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun getHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun getDefaultParamInterceptor(): Interceptor {
        return Interceptor {
            val original = it.request()
            val httpUrl = original.url

            val newHttpUrl = httpUrl.newBuilder().addQueryParameter("app_id", "2602d77305d540f88e008db91f77aba6").build()

            val requestBuilder = original.newBuilder().url(newHttpUrl)
            it.proceed(requestBuilder.build())
        }
    }
}