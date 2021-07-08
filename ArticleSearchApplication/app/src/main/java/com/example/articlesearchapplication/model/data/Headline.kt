package com.example.articlesearchapplication.model.data

import com.google.gson.annotations.SerializedName

data class Headline(
    @SerializedName("main")
    val title: String?
)