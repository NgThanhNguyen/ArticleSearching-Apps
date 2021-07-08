package com.example.articlesearchapplication.`interface`

interface ArticleInterface {
    interface ArticleModel {
        fun getArticleSection(Page: Int)
    }

    interface ArticleView {
        fun onSuccess()
        fun onFailed()
    }

    interface ArticlePresent {
        fun networkCall(Page: Int)
    }
}