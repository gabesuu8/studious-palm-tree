package com.example.helloapp.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.R
import com.example.helloapp.data.Article

class ArticleAdapter(
    private val onItemClick: (Article) -> Unit,
    private val onFavoriteClick: (Article) -> Unit
) : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallback()) {

    private var searchKeywords: List<String> = emptyList()

    fun setSearchKeywords(query: String) {
        searchKeywords = query.lowercase().split(" ").filter { it.isNotBlank() && it.length > 1 }
        notifyItemRangeChanged(0, itemCount, PAYLOAD_HIGHLIGHT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.contains(PAYLOAD_HIGHLIGHT)) {
            holder.bind(getItem(position))
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.articleTitle)
        private val summary: TextView = itemView.findViewById(R.id.articleSummary)
        private val category: TextView = itemView.findViewById(R.id.articleCategory)
        private val source: TextView = itemView.findViewById(R.id.articleSource)
        private val favoriteButton: ImageView = itemView.findViewById(R.id.favoriteButton)

        fun bind(article: Article) {
            if (searchKeywords.isNotEmpty()) {
                title.text = highlightKeywords(article.title, searchKeywords)
                summary.text = highlightKeywords(article.summary, searchKeywords)
                category.text = highlightKeywords(article.category, searchKeywords)
            } else {
                title.text = article.title
                summary.text = article.summary
                category.text = article.category
            }
            source.text = article.source
            
            // Update favorite star icon
            favoriteButton.setImageResource(
                if (article.isFavorite) android.R.drawable.btn_star_big_on
                else android.R.drawable.btn_star_big_off
            )

            itemView.setOnClickListener {
                onItemClick(article)
            }
            
            favoriteButton.setOnClickListener {
                onFavoriteClick(article)
            }
        }
        
        private fun highlightKeywords(text: String, keywords: List<String>): SpannableString {
            val spannable = SpannableString(text)
            val highlightColor = Color.parseColor("#FFEB3B") // Yellow highlight
            val textColor = Color.parseColor("#6200EE") // Purple text for highlighted
            
            keywords.forEach { keyword ->
                var startIndex = text.lowercase().indexOf(keyword)
                while (startIndex >= 0) {
                    val endIndex = startIndex + keyword.length
                    spannable.setSpan(
                        BackgroundColorSpan(highlightColor),
                        startIndex,
                        endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannable.setSpan(
                        ForegroundColorSpan(textColor),
                        startIndex,
                        endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    startIndex = text.lowercase().indexOf(keyword, endIndex)
                }
            }
            return spannable
        }
    }

    companion object {
        private const val PAYLOAD_HIGHLIGHT = "highlight"
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
