package com.example.articlesearchapplication.model.data

import com.google.gson.annotations.SerializedName

data class ArticleRespond(
    @SerializedName("response")
    val respond: Response
)