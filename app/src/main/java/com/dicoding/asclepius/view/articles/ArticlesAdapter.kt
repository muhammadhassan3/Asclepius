package com.dicoding.asclepius.view.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.remote.response.Article
import com.dicoding.asclepius.databinding.ArticleItemBinding

class ArticlesAdapter(private val list: List<Article>, private val onClick: (url: String) -> Unit) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    inner class ArticlesViewHolder(val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticlesViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            title.text = item.title
            Glide.with(holder.itemView.context).load(item.urlToImage).into(headerImage)
        }

        holder.itemView.setOnClickListener {
            onClick.invoke(item.url)
        }
    }
}