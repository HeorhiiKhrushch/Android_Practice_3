package com.example.practice3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val isFavorite: Boolean,
    val previewUrl: String,
    val fullUrl: String
)