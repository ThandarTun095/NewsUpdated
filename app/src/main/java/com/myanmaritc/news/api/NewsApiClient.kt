package com.myanmaritc.news.api

import com.myanmaritc.news.model.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiClient {

    val BASE_URL = "http://newsapi.org/v2/"

    val newsApiInterface: NewsApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApiInterface = retrofit.create(NewsApiInterface::class.java)

    }

    fun getTopHeadlines(country: String, category: String): Call<News>{
        return newsApiInterface.getTopHeadline(country,category)
    }

    fun searchNews(query: String): Call<News> {
        return searchNews(query)
    }
}