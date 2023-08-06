package com.ilyakoz.cryptoapp.domain

import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(fromsymbol : String) = repository.getCoinInfo(fromsymbol)
}