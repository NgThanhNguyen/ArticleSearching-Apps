package com.example.articlesearchapplication.model.api

import com.example.articlesearchapplication.model.data.ArticleRespond
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ArticleAPI {

    @GET("search/v2/articlesearch.json")
    fun getArticle(@Query("q") q: String,
                   @Query("page") page: Int,
                   @Query("api-key") apiKey: String = "f3GeBsFpAV63WrG77OVEADc1AV1p6qE2"


    ): Call<ArticleRespond>
}

