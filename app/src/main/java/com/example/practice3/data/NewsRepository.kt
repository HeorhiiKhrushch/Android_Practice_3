package com.example.practice3.data

import com.example.practice3.data.local.NewsDao
import com.example.practice3.data.network.NewsApiService
import com.example.practice3.data.network.NewsApiService.Companion.generateNewsList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val newsDao: NewsDao, private val newsApiService: NewsApiService) {

    val allNews: Flow<List<News>> = newsDao.getAllNews()

    fun getNewsByIdFlow(newsId: Int): Flow<News> {
        return newsDao.getNewsById(newsId)
    }

    suspend fun refreshNews() {

        /** in a real-world API the following method will be called, but we will replace it with a delay and mocked data
         * val news = [newsApiService].getNews()
         */

        delay(2000L)

        val news = generateNewsList()

        newsDao.insertOrUpdateNews(news)
    }

    suspend fun toggleFavoriteStatus(news: News) {

        val updatedNews = news.copy(isFavorite = !news.isFavorite)

        /** in a real-world API the following method will be called, but we will replace it with a delay and the same updated news
         * val responseNews = [newsApiService].updateNews(updatedNews.id, updatedNews)
         */

        delay(300L)
        val responseNews = updatedNews

        newsDao.insertOrUpdateNews(responseNews)
    }
}