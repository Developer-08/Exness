package com.testing.exness

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountryService {
    @GET("v3.1/all")
    suspend fun getCountries(): List<Country>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val countryService: CountryService = retrofit.create(CountryService::class.java)


