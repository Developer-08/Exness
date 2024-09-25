package com.testing.exness

import com.google.gson.annotations.SerializedName

data class CryptocurrencyResponse(
    val data: Map<String, Cryptocurrency>
)

data class Cryptocurrency(
    val symbol: String,
    val quote: Quote
)

data class Quote(
    @SerializedName("USD") val usd: PriceInfo
)

data class PriceInfo(
    val price: Double,
    @SerializedName("percent_change_24h") val percentChange24h: Double
)



// Country DataModel
data class Country(
    val name: Name,
    val flags: Flags
)
data class Name(
    val common: String // Name of the country
)
data class Flags(
    val png: String // URL of the flag image
)
