package com.example.articlesearchapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.articlesearchapplication.R
import com.example.articlesearchapplication.`interface`.ArticleInterface
import com.example.articlesearchapplication.presenter.ArticlePresent
import com.example.articlesearchapplication.ui.ArticleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ArticleInterface.ArticleView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var LayoutManager: GridLayoutManager
    private lateinit var Adapter: ArticleAdapter
    private var ArticlePage: Int = 1

    private var presenter: ArticlePresent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar: ProgressBar = this.loading
        progressBar.visibility = View.VISIBLE

        presenter = ArticlePresent()
        presenter!!.networkCall(ArticlePage)

        progressBar.visibility = View.GONE

        onSuccess()
    }

    override fun onSuccess() {
        recyclerView = findViewById(R.id.article_list)

        LayoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = LayoutManager

        Adapter = ArticleAdapter(mutableListOf())

        recyclerView.adapter = Adapter
    }

    override fun onFailed() {
        Toast.makeText(this, getString(R.string.error_fetch_articles), Toast.LENGTH_SHORT).show()
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