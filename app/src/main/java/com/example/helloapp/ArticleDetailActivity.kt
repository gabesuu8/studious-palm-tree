package com.example.helloapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helloapp.data.Article
import com.google.android.material.appbar.MaterialToolbar
import android.widget.TextView

class ArticleDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val article = intent.getParcelableExtra<Article>("article") ?: return

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        findViewById<TextView>(R.id.articleTitle).text = article.title
        findViewById<TextView>(R.id.articleContent).text = article.content.replace("\n\n", "\n")
        findViewById<TextView>(R.id.articleCategory).text = article.category
        findViewById<TextView>(R.id.articleSource).text = article.source
    }
}
