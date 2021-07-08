package com.example.articlesearchapplication.model.data

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("docs")
    var Articles: MutableList<Docs>
)