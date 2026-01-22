package com.example.helloapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val summary: String,
    val source: String,
    val dateAdded: Long = System.currentTimeMillis(),
    val category: String = "General Health",
    val isFavorite: Boolean = false
) : Parcelable
