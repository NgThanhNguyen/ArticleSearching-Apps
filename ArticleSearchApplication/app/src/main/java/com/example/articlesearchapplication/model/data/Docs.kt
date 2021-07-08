package com.example.articlesearchapplication.model.data

import com.google.gson.annotations.SerializedName

data class Docs(
    @SerializedName("web_url")
    val web_url: String?,

    @SerializedName("multimedia")
    val multimedia: List<Multimedia>,

    @SerializedName("headline")
    val headline: Headline
)
