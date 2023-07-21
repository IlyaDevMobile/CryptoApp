package com.ilyakoz.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ilyakoz.cryptoapp.domain.CoinInfo

object CoinInfoDiffCallBack:DiffUtil.ItemCallback<CoinInfo>() {

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return  oldItem.fromsymbol == newItem.fromsymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return  oldItem == newItem
    }
}