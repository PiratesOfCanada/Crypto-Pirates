package com.example.cryptoanalysis.data.repo

import com.example.cryptoanalysis.data.api.NewsApi
import com.example.cryptoanalysis.data.api.NewsInterface

import javax.inject.Inject

class NewsRepo @Inject constructor(@NewsApi var newsinter: NewsInterface) {
    fun getAllNews() = newsinter.getAllNews()
}
