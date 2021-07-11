package com.example.articlesearchapplication.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.articlesearchapplication.R
import com.example.articlesearchapplication.`interface`.ArticleInterface
import com.example.articlesearchapplication.model.data.Docs
import com.example.articlesearchapplication.presenter.ArticlePresent
import com.example.articlesearchapplication.ui.ArticleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ArticleInterface.ArticleView, View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: ArticleAdapter
    private var articlePage: Int = 1
    private var isLoading: Boolean = true

    private lateinit var empty: String

    private var presenter: ArticlePresent? = null
    private lateinit var searchBar: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBar = findViewById(R.id.input)
        button = findViewById(R.id.button)

        initAdapter()

        val progressBar: ProgressBar = findViewById(R.id.loading)
        progressBar.visibility = View.VISIBLE

        presenter = ArticlePresent(this)
        presenter?.networkCall(articlePage)

        //progressBar.visibility = View.GONE

        attachOnScrollListener()

        button.setOnClickListener(this)


    }

    private fun initAdapter() {
        recyclerView = findViewById(R.id.article_list)

        layoutManager = GridLayoutManager(this, 3)

        recyclerView.layoutManager = layoutManager

        adapter = ArticleAdapter(mutableListOf()) { Articles -> showWeb(Articles) }

        recyclerView.adapter = adapter
    }

    override fun onSuccess(list: MutableList<Docs>) {
        //adapter.updatearticles(list)
        articlesFetch(list)
        loading.visibility = View.GONE

    }

    override fun onFailed(msg: String) {
        //Log.d("MyTag",msg)
        loading.visibility = View.VISIBLE
    }

    private fun attachOnScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount

                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItem  == totalItemCount - 1) {
                    loading.visibility = View.VISIBLE
                    articlePage++
                    if(presenter?.a == true) {
                        presenter?.searchCall(articlePage,empty)
                    }
                    else {
                        presenter?.networkCall(articlePage)
                    }
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
        val url = Articles.web_url
        val builder = CustomTabsIntent.Builder()
        val customTabIntent = builder.build()
        customTabIntent.launchUrl(this, Uri.parse(url))
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.button) {
            loading.visibility = View.VISIBLE
            presenter?.a = true
            adapter.clearArticle()
            empty = searchBar.text.toString()
            if(empty.isNullOrEmpty()) {
                articlePage = 1
                presenter?.networkCall(articlePage)
            }
            else {
                articlePage = 1
                presenter?.searchCall(articlePage, empty)
            }

        }
    }
}


