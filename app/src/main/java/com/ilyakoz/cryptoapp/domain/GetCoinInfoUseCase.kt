package com.ilyakoz.cryptoapp.domain

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fromsymbol : String) = repository.getCoinInfo(fromsymbol)
}