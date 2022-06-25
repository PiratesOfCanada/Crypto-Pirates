package com.example.cryptoanalysis.ui.discover

import com.example.cryptoanalysis.RetrofitInterface

class DiscoverRepo(var inter : RetrofitInterface) {
    fun getAllCoins() = inter.getAllCoins()
}