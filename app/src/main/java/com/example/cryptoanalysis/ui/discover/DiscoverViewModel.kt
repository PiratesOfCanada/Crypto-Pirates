package com.example.cryptoanalysis.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable

class DiscoverViewModel(var repo : DiscoverRepo) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//    val text: LiveData<String> = _text

    fun getallAPIMoney(): Observable<JsonObject> {
        return repo.getallAPIMoney()
    }
}