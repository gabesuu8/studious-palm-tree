package com.example.helloapp

import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.helloapp.data.Article
import com.example.helloapp.util.LanguageHelper
import com.google.android.material.appbar.MaterialToolbar
import android.widget.TextView

class ArticleDetailActivity : AppCompatActivity() {
    
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLanguage(newBase))
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val article = intent.getParcelableExtra<Article>("article") ?: return

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        findViewById<TextView>(R.id.articleTitle).text = article.title
        findViewById<TextView>(R.id.articleCategory).text = article.category
        findViewById<TextView>(R.id.articleSource).text = "Source: ${article.source}"

        // Render HTML content with CSS styling in WebView
        val webView = findViewById<WebView>(R.id.articleContent)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = false
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        
        val styledHtml = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    * {
                        box-sizing: border-box;
                    }
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
                        font-size: 15px;
                        line-height: 1.7;
                        color: #333;
                        padding: 16px;
                        margin: 0;
                        background-color: #fff;
                    }
                    
                    /* Key Facts Box - WHO Style */
                    .key-facts {
                        background: #f3f3f3;
                        border-left: 4px solid #0093D5;
                        padding: 16px 20px;
                        margin-bottom: 24px;
                    }
                    
                    .key-facts h2 {
                        color: #0093D5;
                        font-size: 18px;
                        font-weight: 700;
                        margin: 0 0 12px 0;
                        padding: 0;
                        border: none;
                    }
                    
                    .key-facts ul {
                        margin: 0;
                        padding-left: 20px;
                    }
                    
                    .key-facts li {
                        margin-bottom: 8px;
                        color: #333;
                    }
                    
                    /* Section Headers - WHO Style */
                    h2 {
                        color: #333;
                        font-size: 20px;
                        font-weight: 700;
                        margin: 28px 0 16px 0;
                        padding: 0;
                        border: none;
                    }
                    
                    h3 {
                        color: #333;
                        font-size: 16px;
                        font-weight: 700;
                        margin: 20px 0 10px 0;
                        padding: 0;
                        border: none;
                    }
                    
                    /* Paragraphs */
                    p {
                        margin: 0 0 16px 0;
                    }
                    
                    /* Lists - WHO Style */
                    ul {
                        margin: 0 0 16px 0;
                        padding-left: 20px;
                    }
                    
                    li {
                        margin-bottom: 8px;
                        line-height: 1.6;
                    }
                    
                    /* Horizontal rule - WHO Style */
                    hr {
                        border: none;
                        border-top: 1px solid #ddd;
                        margin: 24px 0;
                    }
                    
                    /* Links - WHO Style */
                    a {
                        color: #0093D5;
                        text-decoration: underline;
                    }
                    
                    /* Warning/Important text */
                    .warning {
                        color: #c00;
                        font-weight: 600;
                    }
                    
                    /* Highlight box for important info */
                    .highlight-box {
                        background: #fff8e1;
                        border-left: 4px solid #ffc107;
                        padding: 12px 16px;
                        margin: 16px 0;
                    }
                    
                    /* Sources section */
                    .sources {
                        background: #f9f9f9;
                        padding: 16px;
                        margin-top: 24px;
                        border-top: 2px solid #0093D5;
                    }
                    
                    .sources h2 {
                        color: #0093D5;
                        font-size: 18px;
                        margin-top: 0;
                    }
                    
                    .source-item {
                        margin: 12px 0;
                        padding-bottom: 12px;
                        border-bottom: 1px solid #eee;
                    }
                    
                    .source-item:last-child {
                        border-bottom: none;
                        padding-bottom: 0;
                    }
                    
                    .source-name {
                        font-weight: 600;
                        color: #333;
                        margin-bottom: 4px;
                    }
                    
                    /* Bold text */
                    strong, b {
                        font-weight: 600;
                    }
                    
                    /* Italic */
                    em, i {
                        font-style: italic;
                        color: #666;
                    }
                </style>
            </head>
            <body>
                ${article.content}
            </body>
            </html>
        """.trimIndent()
        
        webView.loadDataWithBaseURL(null, styledHtml, "text/html", "UTF-8", null)
    }
}
