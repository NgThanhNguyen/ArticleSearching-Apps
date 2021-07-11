package com.example.articlesearchapplication.presenter

import com.example.articlesearchapplication.`interface`.ArticleInterface
import com.example.articlesearchapplication.model.data.Docs
import com.example.articlesearchapplication.repos.ArticleRepos

class ArticlePresent(articleView: ArticleInterface.ArticleView): ArticleInterface.ArticlePresent {
    private val model: ArticleInterface.ArticleModel = ArticleRepos(this::onSuccess, this::onError)
    private val view: ArticleInterface.ArticleView = articleView
    var a: Boolean = false
    override fun networkCall(Page: Int) {
        a = false
        model?.getArticleSection(Page)
    }

    override fun searchCall(Page: Int, Query: String) {
        a = true
        model?.getArticleByQuery(Page,Query)
    }

    private fun onSuccess(list: MutableList<Docs>) {
        view.onSuccess(list)
    }

    private fun onError(msg: String) {
        view.onFailed(msg)
    }
}