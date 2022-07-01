package com.example.cryptoanalysis.data.repo

import com.example.cryptoanalysis.NewsInterface

class NewsRepo(var newsinter: NewsInterface) {
    fun getAllNews() = newsinter.getAllNews()
}
