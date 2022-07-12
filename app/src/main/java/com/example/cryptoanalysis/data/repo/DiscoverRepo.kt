package com.example.cryptoanalysis.data.repo

import com.example.cryptoanalysis.data.api.DiscoverApi
import com.example.cryptoanalysis.data.api.RetrofitInterface

import javax.inject.Inject


class DiscoverRepo @Inject constructor (@DiscoverApi var inter : RetrofitInterface)
{

    fun getAllCoins(limit:Int, offset: Int) = inter.getAllCoins(limit, offset)
}