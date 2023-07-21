package com.ilyakoz.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "full_price_list2")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromsymbol: String,
    val tosymbol: String? = null,
    val price: Double? = null,
    val lastupdate: Int? = null,
    val highday: Double? = null,
    val lowday: Double? = null,
    val lastmarket: String? = null,
    val imageurl: String?
)

