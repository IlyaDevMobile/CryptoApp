package com.ilyakoz.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ilyakoz.cryptoapp.data.repository.CoinRepositoryImpl
import com.ilyakoz.cryptoapp.domain.GetCoinInfoListUseCase
import com.ilyakoz.cryptoapp.domain.GetCoinInfoUseCase
import com.ilyakoz.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {


    val coinInfoList = getCoinInfoListUseCase()


    fun getDetailInfo(fsym: String) = getCoinInfoUseCase(fsym)


    init {
        loadDataUseCase()


    }


}