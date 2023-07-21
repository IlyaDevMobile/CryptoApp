package com.ilyakoz.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.ilyakoz.cryptoapp.data.database.AppDatabase
import com.ilyakoz.cryptoapp.data.mapper.CoinMapper
import com.ilyakoz.cryptoapp.data.network.ApiFactory
import com.ilyakoz.cryptoapp.domain.CoinInfo
import com.ilyakoz.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application
): CoinRepository {

    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map { list ->
            list.map { mapper.mapDbModelToEntity(it) }
        }
    }

    override fun getCoinInfo(fromsymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoAboutCoin(fromsymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true){
            try {
                val topCoins = apiService.getTopCoinInfo(limit = 50)
                val fsyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fsyms = fsyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10_000)
        }


    }
}