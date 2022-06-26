package com.example.cryptoanalysis.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoanalysis.Coin
import com.example.cryptoanalysis.ResponseCoins
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor (var repo : DiscoverRepo) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//    val text: LiveData<String> = _text

    fun getAllCoins(): Observable<ResponseCoins> {

        return repo.getAllCoins()
    }
}