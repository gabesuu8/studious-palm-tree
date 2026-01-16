package com.example.helloapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY dateAdded DESC")
    fun getAllArticles(): Flow<List<Article>>
    
    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticleById(id: Long): Article?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)
    
    @Delete
    suspend fun deleteArticle(article: Article)
    
    @Query("DELETE FROM articles")
    suspend fun deleteAllArticles()
}
