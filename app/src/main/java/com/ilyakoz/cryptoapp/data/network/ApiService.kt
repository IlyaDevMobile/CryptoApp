package com.ilyakoz.cryptoapp.data.network

import com.ilyakoz.cryptoapp.data.model.CoinInfoListOfData
import com.ilyakoz.cryptoapp.data.model.CoinPriceInfoRawData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinInfo(
        @Query(Query_PARAM_TO_API_KEY) apiKey: String = "",
        @Query(Query_PARAM_LIMIT) limit: Int = LIMIT_OF_NUMBER_RECORDS,
        @Query(Query_PARAM_TO_SYMBOL) tsym: String = CURRENCY_USD,
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(Query_PARAM_TO_API_KEY) apiKey: String = "",
        @Query(Query_PARAM_FROM_SYMBOL) fsyms: String = CRYPTO_BITCOIN,
        @Query(Query_PARAM_TO_SYMBOLS) tsyms: String = CURRENCY_USD,


        ): Single<CoinPriceInfoRawData>

    companion object {
        private const val Query_PARAM_LIMIT = "limit"
        private const val Query_PARAM_TO_SYMBOL = "tsym"
        private const val Query_PARAM_FROM_SYMBOL = "fsyms"

        private const val Query_PARAM_TO_SYMBOLS = "tsyms"
        private const val Query_PARAM_TO_API_KEY = "api_key"

        private const val CURRENCY_USD = "USD"
        private const val CRYPTO_BITCOIN = "BTC"

        private const val LIMIT_OF_NUMBER_RECORDS = 10
    }


}