package com.example.cryptoanalysis


import com.example.cryptoanalysis.ui.discover.DiscoverRepo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Observable

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    var baseurl = "https://coinranking1.p.rapidapi.com/";

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(baseurl)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RetrofitInterface = retrofit.create(RetrofitInterface::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: RetrofitInterface) = DiscoverRepo(apiService)
}


interface RetrofitInterface {

    @GET("coins?referenceCurrencyUuid=yhjMzLPhuIDl&timePeriod=24h&orderBy=marketCap&orderDirection=desc&limit=100&offset=0")
    @Headers("X-Rapidapi-Key: 870ba89cf6msh2b32fb922a04c36p1ab840jsn47d713b4fe85")
    fun getAllCoins() : Observable<ResponseCoins>

}