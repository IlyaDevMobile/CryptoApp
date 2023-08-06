package com.ilyakoz.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.ilyakoz.cryptoapp.data.database.AppDatabase
import com.ilyakoz.cryptoapp.data.database.CoinInfoDao
import com.ilyakoz.cryptoapp.data.mapper.CoinMapper
import com.ilyakoz.cryptoapp.data.network.ApiFactory
import com.ilyakoz.cryptoapp.data.workers.RefreshDataWorker
import com.ilyakoz.cryptoapp.domain.CoinInfo
import com.ilyakoz.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val mapper : CoinMapper,
    private val application: Application,
    private val coinInfoDao : CoinInfoDao
) : CoinRepository {

    private val apiService = ApiFactory.apiService


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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()

        )


    }
}