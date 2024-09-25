package com.testing.exness.api

import com.testing.exness.CryptocurrencyResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface CoinMarketCapApiService {
    @GET("cryptocurrency/quotes/latest")
    suspend fun getCryptoPrices(
        @Query("symbol") symbols: String,
        @Query("convert") convert: String = "USD"
    ): CryptocurrencyResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://pro-api.coinmarketcap.com/v1/"
    private const val API_KEY = "f699f4aa-2ded-450d-af3f-c15c2926c298"

    private val client = OkHttpClient.Builder().apply {
        addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-CMC_PRO_API_KEY", API_KEY)
                .build()
            chain.proceed(request)
        }
    }.build()

    val api: CoinMarketCapApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinMarketCapApiService::class.java)
    }
}