package com.example.helloapp.repository

import com.example.helloapp.data.Article
import com.example.helloapp.data.ArticleDao
import kotlinx.coroutines.flow.Flow

class ArticleRepository(private val articleDao: ArticleDao) {
    val allArticles: Flow<List<Article>> = articleDao.getAllArticles()
    val favoriteArticles: Flow<List<Article>> = articleDao.getFavoriteArticles()
    val allCategories: Flow<List<String>> = articleDao.getAllCategories()
    
    suspend fun getArticleById(id: Long): Article? {
        return articleDao.getArticleById(id)
    }
    
    suspend fun toggleFavorite(articleId: Long, isFavorite: Boolean) {
        articleDao.updateFavoriteStatus(articleId, isFavorite)
    }
    
    suspend fun insertArticle(article: Article) {
        articleDao.insertArticle(article)
    }
    
    suspend fun insertArticles(articles: List<Article>) {
        articleDao.insertArticles(articles)
    }
    
    suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }
    
    suspend fun deleteAllArticles() {
        articleDao.deleteAllArticles()
    }
}
