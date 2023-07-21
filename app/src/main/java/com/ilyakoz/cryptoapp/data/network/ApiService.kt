package com.ilyakoz.cryptoapp.data.network

import com.ilyakoz.cryptoapp.data.network.model.CoinNamesListDto
import com.ilyakoz.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinInfo(
        @Query(Query_PARAM_TO_API_KEY) apiKey: String = "",
        @Query(Query_PARAM_LIMIT) limit: Int = 15,
        @Query(Query_PARAM_TO_SYMBOL) tsym: String = CURRENCY_USD,
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(Query_PARAM_TO_API_KEY) apiKey: String = "",
        @Query(Query_PARAM_FROM_SYMBOL) fsyms: String,
        @Query(Query_PARAM_TO_SYMBOLS) tsyms: String = CURRENCY_USD,
        ): CoinInfoJsonContainerDto

    companion object {
        private const val Query_PARAM_LIMIT = "limit"
        private const val Query_PARAM_TO_SYMBOL = "tsym"
        private const val Query_PARAM_FROM_SYMBOL = "fsyms"

        private const val Query_PARAM_TO_SYMBOLS = "tsyms"
        private const val Query_PARAM_TO_API_KEY = "api_key"

        private const val CURRENCY_USD = "USD"

    }


}