package com.example.helloapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.adapter.ArticleAdapter
import com.example.helloapp.data.Article
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class FavoritesActivity : AppCompatActivity() {
    private lateinit var viewModel: ArticleViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateLayout: LinearLayout
    private lateinit var adapter: ArticleAdapter

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLanguage(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        // Setup toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        // Initialize views
        recyclerView = findViewById(R.id.favoritesRecyclerView)
        emptyStateLayout = findViewById(R.id.emptyStateLayout)

        // Initialize adapter
        adapter = ArticleAdapter(
            onItemClick = { article -> openArticleDetail(article) },
            onFavoriteClick = { article -> toggleFavorite(article) }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Initialize ViewModel
        viewModel = ViewModelProvider(
            this,
            ArticleViewModel.AndroidViewModelFactory.getInstance(application)
        )[ArticleViewModel::class.java]

        // Observe favorite articles
        lifecycleScope.launch {
            viewModel.favoriteArticles.collect { favorites ->
                if (favorites.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    emptyStateLayout.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    emptyStateLayout.visibility = View.GONE
                    adapter.submitList(favorites)
                }
            }
        }
    }

    private fun openArticleDetail(article: Article) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }

    private fun toggleFavorite(article: Article) {
        viewModel.toggleFavorite(article)
        val message = if (article.isFavorite) {
            getString(R.string.removed_from_favorites)
        } else {
            getString(R.string.added_to_favorites)
        }
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show()
    }
}
