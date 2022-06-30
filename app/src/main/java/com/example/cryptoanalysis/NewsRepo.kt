package com.example.cryptoanalysis

class NewsRepo(var newsinter: NewsInterface) {
    fun getAllNews() = newsinter.getAllNews()
}
