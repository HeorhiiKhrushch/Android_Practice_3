package com.example.practice3.data.local

import androidx.room.*
import com.example.practice3.data.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news ORDER BY id ASC")
    fun getAllNews(): Flow<List<News>>

    @Query("SELECT * FROM news WHERE id = :newsId")
    fun getNewsById(newsId: Int): Flow<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateNews(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateNews(news: List<News>)
}