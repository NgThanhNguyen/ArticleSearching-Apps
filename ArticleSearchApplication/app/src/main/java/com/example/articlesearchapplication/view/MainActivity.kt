package com.example.articlesearchapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.articlesearchapplication.R
import com.example.articlesearchapplication.`interface`.ArticleInterface
import com.example.articlesearchapplication.model.data.Docs
import com.example.articlesearchapplication.presenter.ArticlePresent
import com.example.articlesearchapplication.ui.ArticleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ArticleInterface.ArticleView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: ArticleAdapter
    private var ArticlePage: Int = 1

    private var presenter: ArticlePresent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()

        val progressBar: ProgressBar = this.loading
        progressBar.visibility = View.VISIBLE

        presenter = ArticlePresent(this)
        presenter!!.networkCall(ArticlePage)

        progressBar.visibility = View.GONE

    }

    fun initAdapter() {
        recyclerView = findViewById(R.id.article_list)

        layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager

        adapter = ArticleAdapter(mutableListOf())

        recyclerView.adapter = adapter
    }

    override fun onSuccess(list: List<Docs>) {
        adapter.updatearticles(list)
    }

    override fun onFailed(msg: String) {

    }

}

/*    private fun getArticle() {
        ArticleServices.getArticle(
            ArticlePage
        )
    }*/

/*    private fun ArticlesFetch(rp: MutableList<Docs>) {
        Adapter.updatearticles(rp)
    }
 */