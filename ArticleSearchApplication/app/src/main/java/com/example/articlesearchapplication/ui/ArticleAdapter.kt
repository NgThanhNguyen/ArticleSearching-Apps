package com.example.articlesearchapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.articlesearchapplication.R
import com.example.articlesearchapplication.model.data.Docs
import kotlinx.android.synthetic.main.article_item.view.*

class ArticleAdapter(private var articles: MutableList<Docs>, private val onMovieClick: (Articles: Docs) -> Unit
): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun appendArticleList(Articles: MutableList<Docs>) {
        articles.addAll(Articles)
        notifyDataSetChanged()
    }

    fun updatearticles(rp: MutableList<Docs>) {
        articles = rp
        notifyDataSetChanged()
    }

    fun clearArticle() {
        articles.clear()
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.article_image)

        fun bind(articles: Docs) {
                itemView.title.text = articles.headline.title
            if(!articles.multimedia[0].image.isNullOrEmpty()) {
                Glide.with(itemView)
                    .load("http://static01.nyt.com/${articles.multimedia[0].image}").error(R.drawable.ic_launcher_background)
                    .transform(CenterCrop())
                    .into(poster)
            }
            itemView.setOnClickListener { onMovieClick.invoke(articles) }
        }
    }

}
