package com.ilyakoz.cryptoapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.ilyakoz.cryptoapp.data.database.AppDatabase
import com.ilyakoz.cryptoapp.data.mapper.CoinMapper
import com.ilyakoz.cryptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context, workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {


    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService

    private val coinInfoDao = AppDatabase.getInstance(context).coinPriceInfoDao()
    override suspend fun doWork(): Result {
        while (true) {
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

    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}