package com.example.cryptoanalysis.data.repo

import android.util.Log
import com.example.cryptoanalysis.Api
import com.example.cryptoanalysis.RetrofitInterface

class DiscoverRepo(var inter : RetrofitInterface)
{

    fun getAllCoins(limit:Int, offset: Int) = inter.getAllCoins(limit, offset)
}