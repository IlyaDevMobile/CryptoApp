package com.ilyakoz.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ilyakoz.cryptoapp.adapters.CoinInfoAdapter
import com.ilyakoz.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.ilyakoz.cryptoapp.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCoinPriceListBinding

    private val viewModel: CoinViewModel by lazy {
        CoinViewModel(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("TEST_CLICK",coinPriceInfo.fromsymbol)
            }
        }
        binding.rvCoinPriceList.adapter = adapter

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })


    }


}