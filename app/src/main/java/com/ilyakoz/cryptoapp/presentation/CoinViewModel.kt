package com.ilyakoz.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ilyakoz.cryptoapp.data.repository.CoinRepositoryImpl
import com.ilyakoz.cryptoapp.domain.GetCoinInfoListUseCase
import com.ilyakoz.cryptoapp.domain.GetCoinInfoUseCase
import com.ilyakoz.cryptoapp.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()


    fun getDetailInfo(fsym: String) = getCoinInfoUseCase(fsym)


    init {
        loadDataUseCase()


    }


}