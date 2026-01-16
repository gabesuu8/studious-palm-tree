package com.example.helloapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.R
import com.example.helloapp.data.Article

class ArticleAdapter(
    private val onItemClick: (Article) -> Unit
) : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.articleTitle)
        private val summary: TextView = itemView.findViewById(R.id.articleSummary)
        private val category: TextView = itemView.findViewById(R.id.articleCategory)
        private val source: TextView = itemView.findViewById(R.id.articleSource)

        fun bind(article: Article) {
            title.text = article.title
            summary.text = article.summary
            category.text = article.category
            source.text = article.source

            itemView.setOnClickListener {
                onItemClick(article)
            }
        }
    }

    class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
