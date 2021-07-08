package com.example.articlesearchapplication.presenter

import com.example.articlesearchapplication.`interface`.ArticleInterface
import com.example.articlesearchapplication.repos.ArticleRepos

class ArticlePresent: ArticleInterface.ArticlePresent {
    private val model: ArticleInterface.ArticleModel= ArticleRepos()
    override fun networkCall(Page: Int) {
        model?.getArticleSection(Page)
    }
}