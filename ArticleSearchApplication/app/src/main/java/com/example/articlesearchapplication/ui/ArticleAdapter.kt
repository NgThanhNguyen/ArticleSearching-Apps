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

class ArticleAdapter(private var Articles: MutableList<Docs>,
): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(Articles[position])
    }

    override fun getItemCount(): Int {
        return this.Articles.size
    }

    fun updatearticles(rp: MutableList<Docs>) {
        this.Articles = rp
        notifyDataSetChanged()
    }

    fun clear() {
        Articles.clear()
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.article_image)

        fun bind(Articles: Docs) {
            itemView.title.text = Articles.headline.title
                Glide.with(itemView)
                    .load(Articles.multimedia[0].image)
                    .transform(CenterCrop())
                    .into(poster)
            }
        }
    }
