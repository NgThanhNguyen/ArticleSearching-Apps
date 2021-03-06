package com.example.articlesearchapplication.`interface`

import com.example.articlesearchapplication.model.data.Docs

interface ArticleInterface {
    interface ArticleModel {
        fun getArticleSection(Page: Int)
        fun getArticleByQuery(Page: Int, Query: String)
    }

    interface ArticleView {
        fun onSuccess(list: MutableList<Docs>)
        fun onFailed(msg: String)
    }

    interface ArticlePresent {
        fun networkCall(Page: Int)
        fun searchCall(Page: Int, Query: String)
    }
}