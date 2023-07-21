package com.ilyakoz.cryptoapp.data.mapper

import com.google.gson.Gson
import com.ilyakoz.cryptoapp.data.database.CoinInfoDbModel
import com.ilyakoz.cryptoapp.data.network.model.CoinInfoDto
import com.ilyakoz.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.ilyakoz.cryptoapp.data.network.model.CoinNamesListDto
import com.ilyakoz.cryptoapp.domain.CoinInfo

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel {
        return CoinInfoDbModel(
            fromsymbol = dto.fromsymbol,
            tosymbol = dto.tosymbol,
            price = dto.price,
            lastupdate = dto.lastupdate,
            highday = dto.highday,
            lowday = dto.lowday,
            lastmarket = dto.lastmarket,
            imageurl = dto.imageurl
        )
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map { it.coinName?.name }?.joinToString(",").toString() ?: ""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo {
        return CoinInfo(
            fromsymbol = dbModel.fromsymbol,
            tosymbol = dbModel.tosymbol,
            price = dbModel.price,
            lastupdate = dbModel.lastupdate,
            highday = dbModel.highday,
            lowday = dbModel.lowday,
            lastmarket = dbModel.lastmarket,
            imageurl = dbModel.imageurl
        )

    }
}