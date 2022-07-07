package com.example.cryptoanalysis.data.repo

import com.example.cryptoanalysis.data.api.FavCoinRetroApiInterface
import okhttp3.RequestBody

class FavouriteCoinRepository(private val inter: FavCoinRetroApiInterface) {
    suspend fun getAllFavouriteCoins() = inter.getFavouriteCoins()
    suspend fun getFavouriteCoin(id: String) = inter.getFavouriteCoin(id)
    suspend fun saveFavouriteCoin(requestBody: RequestBody) = inter.saveFavouriteCoin(requestBody)
    suspend fun deleteFavouriteCoin(id: String) = inter.deleteFavouriteCoin(id)
}