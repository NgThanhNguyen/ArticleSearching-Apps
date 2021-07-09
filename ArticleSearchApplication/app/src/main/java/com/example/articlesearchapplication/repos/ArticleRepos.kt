package com.example.articlesearchapplication.repos

import android.util.Log
import com.example.articlesearchapplication.`interface`.ArticleInterface
import com.example.articlesearchapplication.model.api.ArticleAPI
import com.example.articlesearchapplication.model.data.ArticleRespond
import com.example.articlesearchapplication.model.data.Docs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleRepos(
    private var onSuccess: (list: List<Docs>) -> Unit,
    private var onError: (msg: String) -> Unit
): ArticleInterface.ArticleModel {

    override fun getArticleSection(Page: Int) {
        callAPI(Page = Page)
    }

    fun callAPI(Page: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArticleAPI::class.java)

        val q: String = "hero"
        val Data = retrofit.getArticle(q = q,page = Page).enqueue(object: Callback<ArticleRespond> {
            override fun onResponse(
                call: Call<ArticleRespond>,
                response: Response<ArticleRespond>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        Log.d("Repository", "Article: ${responseBody.respond.Articles[0].headline.title}")
                        Log.d("Repository", "Web_URL: ${responseBody.respond.Articles[0].web_url}")
                        Log.d("Repository", "Article: ${responseBody.respond.Articles[0].multimedia[0].image}")
                        onSuccess.invoke(responseBody.respond.Articles)
                    } else {
                        Log.d("Repository", "Failed to get response")
                        onError.invoke(response.message())
                    }
                } else {
                    Log.d("Repository", "Failed to get response")
                    onError.invoke(response.message())
                }
            }

            override fun onFailure(call: Call<ArticleRespond>, t: Throwable) {
                Log.e("Repository", "onFailure", t)
                onError.invoke(t.message.toString())
            }
        })
    }
}