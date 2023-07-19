package com.ilyakoz.cryptoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilyakoz.cryptoapp.pojo.CoinPriceInfo


@Dao
interface CoinPriceInfoDao {

    @Query("SELECT * FROM full_price_list2 ORDER BY lastUpdate")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>


    @Query("SELECT * FROM full_price_list2 WHERE fromsymbol == :fsym LIMIT 1 ")
    fun getPriceInfoAboutCoin(fsym: String): LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)
}