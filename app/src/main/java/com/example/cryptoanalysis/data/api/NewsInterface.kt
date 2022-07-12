package com.example.cryptoanalysis.data.api

import com.example.cryptoanalysis.data.model.ResponseNews
import com.example.cryptoanalysis.utils.AccessToken
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsInterface1 {

    @GET("/v1/all?source=cnn.com&sortBy=date&apiKey=ae2b2187-86cb-4da4-bf48-e413951b0f42&topic=Cryptocurrency")
    fun getAllNews() : Observable<ResponseNews>


    companion object {
        private const val BASE_URL = "https://api.goperigon.com"
        fun create(): NewsInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(NewsInterface::class.java)
        }
    }
}