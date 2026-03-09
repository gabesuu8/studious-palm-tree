package com.example.helloapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY id ASC")
    fun getAllArticles(): Flow<List<Article>>
    
    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticleById(id: Long): Article?
    
    @Query("SELECT * FROM articles WHERE isFavorite = 1 ORDER BY dateAdded DESC")
    fun getFavoriteArticles(): Flow<List<Article>>
    
    @Query("UPDATE articles SET isFavorite = :isFavorite WHERE id = :articleId")
    suspend fun updateFavoriteStatus(articleId: Long, isFavorite: Boolean)
    
    @Query("SELECT DISTINCT category FROM articles ORDER BY category")
    fun getAllCategories(): Flow<List<String>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)
    
    @Delete
    suspend fun deleteArticle(article: Article)
    
    @Query("DELETE FROM articles")
    suspend fun deleteAllArticles()
}
