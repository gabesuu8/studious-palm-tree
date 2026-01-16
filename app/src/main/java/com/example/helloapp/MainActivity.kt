package com.example.helloapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.adapter.ArticleAdapter
import com.example.helloapp.data.Article
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: ArticleViewModel by viewModels {
        ArticleViewModel.AndroidViewModelFactory.getInstance(application)
    }
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateText: TextView
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.articlesRecyclerView)
        emptyStateText = findViewById(R.id.emptyStateText)
        
        adapter = ArticleAdapter { article ->
            openArticleDetail(article)
        }
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabRefresh).setOnClickListener {
            viewModel.fetchAndSaveArticles()
            Snackbar.make(it, "Fetching articles...", Snackbar.LENGTH_SHORT).show()
        }

        // Observe articles
        lifecycleScope.launch {
            var isFirstLoad = true
            viewModel.allArticles.collect { articles ->
                if (articles.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    emptyStateText.visibility = View.VISIBLE
                    // Load articles on first launch if empty
                    if (isFirstLoad) {
                        isFirstLoad = false
                        viewModel.fetchAndSaveArticles()
                    }
                } else {
                    recyclerView.visibility = View.VISIBLE
                    emptyStateText.visibility = View.GONE
                    adapter.submitList(articles)
                    isFirstLoad = false
                }
            }
        }
    }

    private fun openArticleDetail(article: Article) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
}
