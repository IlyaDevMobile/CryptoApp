package com.ilyakoz.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class CoinPriceListActivity : AppCompatActivity() {


    private val viewModel: CoinViewModel by lazy {
        CoinViewModel(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
//        viewModel.priceList.observe(this, Observer {
//            Log.d("TEST_OF_LOADING_DATA","ГОтова в активити $it")
//        })
        viewModel.getDetailInfo("BTC").observe(this,Observer{
                        Log.d("TEST_OF_LOADING_DATA","ГОтова в активити $it")
        })



    }


}