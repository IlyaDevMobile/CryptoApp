package com.ilyakoz.cryptoapp.presentation

import android.app.Application
import com.ilyakoz.cryptoapp.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}