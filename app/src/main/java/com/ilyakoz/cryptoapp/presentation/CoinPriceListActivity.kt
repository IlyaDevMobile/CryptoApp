package com.ilyakoz.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ilyakoz.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.ilyakoz.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.ilyakoz.cryptoapp.data.network.model.CoinInfoDto
import com.ilyakoz.cryptoapp.domain.CoinInfo

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
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromsymbol
                )
                startActivity(intent)
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        viewModel.coinInfoList.observe(this, Observer {
            adapter.coinInfoList = it
        })
    }
}





