package com.ilyakoz.cryptoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ilyakoz.cryptoapp.R
import com.ilyakoz.cryptoapp.databinding.ItemCoinInfoBinding
import com.ilyakoz.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso


class CoinInfoAdapter(
    private val context: Context
) : ListAdapter<CoinInfo, CoinInfoAdapter.CoinInfoViewHolder>(CoinInfoDiffCallBack) {


    var onCoinClickListener: OnCoinClickListener? = null

    inner class CoinInfoViewHolder(val binding: ItemCoinInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        return CoinInfoViewHolder(
            ItemCoinInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromsymbol, tosymbol)
                tvPrice.text = price.toString()
                tvLastUpdate.text =
                    String.format(lastUpdateTemplate, lastupdate)
                Picasso.get().load(imageurl).into(ivLogoCoin)
                holder.itemView.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }
    }


    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }

}