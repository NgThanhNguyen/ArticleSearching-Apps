package com.example.articlesearchapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
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
    private var articlePage: Int = 1
    private var visibleThreshold = 2

    private var presenter: ArticlePresent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()

        val progressBar: ProgressBar = findViewById(R.id.loading)
        progressBar.visibility = View.VISIBLE

        presenter = ArticlePresent(this)
        presenter?.networkCall(articlePage)

        progressBar.visibility = View.GONE

        attachPopularMoviesOnScrollListener()

    }

    fun initAdapter() {
        recyclerView = findViewById(R.id.article_list)

        layoutManager = GridLayoutManager(this, 3)

        recyclerView.layoutManager = layoutManager

        adapter = ArticleAdapter(mutableListOf()) { Articles -> showWeb(Articles) }

        recyclerView.adapter = adapter
    }

    override fun onSuccess(list: MutableList<Docs>) {
        //adapter.updatearticles(list)
        articlesFetch(list)
        //Log.d("MyTag",list.toString())
    }

    override fun onFailed(msg: String) {
        //Log.d("MyTag",msg)
        onError()
    }

    private fun attachPopularMoviesOnScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount
                // The total number of movies in MoviesAdapter
                //val visibleItemCount = layoutManager.childCount
                //
                //val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItem  == totalItemCount - 1) {
                    recyclerView.removeOnScrollListener(this)
                    articlePage++
                    presenter?.networkCall(articlePage)
                }
            }
        })
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_articles), Toast.LENGTH_SHORT).show()
    }

    private fun articlesFetch(list: MutableList<Docs>) {
        adapter.appendArticleList(list)
    }

    private fun showWeb(Articles: Docs) {
        
    }

}


