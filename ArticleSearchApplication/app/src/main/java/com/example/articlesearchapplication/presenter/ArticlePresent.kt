package com.example.articlesearchapplication.presenter

import com.example.articlesearchapplication.`interface`.ArticleInterface
import com.example.articlesearchapplication.model.data.Docs
import com.example.articlesearchapplication.repos.ArticleRepos

class ArticlePresent(articleView: ArticleInterface.ArticleView): ArticleInterface.ArticlePresent {
    private val model: ArticleInterface.ArticleModel = ArticleRepos(this::onSuccess, this::onError)
    private val view: ArticleInterface.ArticleView = articleView
    override fun networkCall(Page: Int) {
        model?.getArticleSection(Page)
    }

    private fun onSuccess(list: List<Docs>) {
        view.onSuccess(list)
    }

    private fun onError(msg: String) {
        view.onFailed(msg)
    }
}