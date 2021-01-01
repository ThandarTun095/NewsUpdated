package com.myanmaritc.news.api

import com.myanmaritc.news.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiInterface {

    @Headers("X-Api-Key: 2a28bf068eb042d58913c32c0da7cd28")
    @GET("top-headlines")
    fun getTopHeadline(
        @Query("country") country: String,
        @Query("category") category: String
    ): Call<News>

    @Headers("X-Api-Key: 2a28bf068eb042d58913c32c0da7cd28")
    @GET("everything")
    fun searchNews(
        @Query("q") query: String
    ): Call<News>


}