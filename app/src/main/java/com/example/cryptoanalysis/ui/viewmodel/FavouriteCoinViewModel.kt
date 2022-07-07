package com.example.cryptoanalysis.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoanalysis.data.model.Coin
import com.example.cryptoanalysis.data.model.FavouriteCoin
import com.example.cryptoanalysis.data.repo.FavouriteCoinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class FavouriteCoinViewModel(private val repo: FavouriteCoinRepository): ViewModel() {
    var favouriteCoinList = MutableLiveData<List<Coin>>()
    var favouriteCoin = MutableLiveData<Coin>()
    var job: Job? = null

    fun getAllFavouriteCoins() {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getAllFavouriteCoins()
            if(res.isSuccessful) {
                favouriteCoinList.postValue(res.body())
            } else {
                println("Not successful")
            }
        }
    }

    fun getFavouriteCoin(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getFavouriteCoin(id)
            if(res.isSuccessful) {
                favouriteCoin.postValue(res.body())
            } else {
                println("Not successful")
            }
        }
    }

    fun saveFavouriteCoin(reqBody: RequestBody) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.saveFavouriteCoin(reqBody)
            if(res.isSuccessful) {
//                favouriteCoinList.postValue(res.body())
                println("Success")
            } else {
                println("Not successful")
            }
        }
    }

    fun deleteFavouriteCoin(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.deleteFavouriteCoin(id)
//            if(res.isSuccessful) {
//                favouriteCoinList.postValue(res.body())
//            } else {
//                println("Not successful")
//            }
        }
    }
}