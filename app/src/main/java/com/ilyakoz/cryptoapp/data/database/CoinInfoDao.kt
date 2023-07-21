package com.ilyakoz.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CoinInfoDao {

    @Query("SELECT * FROM full_price_list2 ORDER BY lastUpdate DESC")
    fun getPriceList(): LiveData<List<CoinInfoDbModel>>


    @Query("SELECT * FROM full_price_list2 WHERE fromsymbol == :fsym LIMIT 1 ")
    fun getPriceInfoAboutCoin(fsym: String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinInfoDbModel>)
}