package com.ilyakoz.cryptoapp.domain


data class CoinInfo(
    val fromsymbol: String,
    val tosymbol: String? = null,
    val price: Double? = null,
    val lastupdate: String? = null,
    val highday: Double? = null,
    val lowday: Double? = null,
    val lastmarket: String? = null,
    val imageurl: String?
)