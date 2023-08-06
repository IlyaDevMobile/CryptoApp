package com.ilyakoz.cryptoapp.di

import android.app.Application
import com.ilyakoz.cryptoapp.data.database.AppDatabase
import com.ilyakoz.cryptoapp.data.database.CoinInfoDao
import com.ilyakoz.cryptoapp.data.repository.CoinRepositoryImpl
import com.ilyakoz.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {


        @Provides
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}