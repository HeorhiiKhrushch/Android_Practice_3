package com.example.practice3.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.practice3.data.News
import com.example.practice3.data.NewsRepository
import com.example.practice3.data.local.NewsDatabase
import com.example.practice3.data.network.NewsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val allNews: Flow<List<News>> = repository.allNews

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.refreshNews()
        }
    }

    fun getNewsByIdFlow(newsId: Int) = repository.getNewsByIdFlow(newsId)

    fun toggleFavoriteStatus(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavoriteStatus(news)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])

                val newsDao = NewsDatabase.getDatabase(application).newsDao()
                val newsApiService = NewsApiService.createNewsApiService()
                val repository = NewsRepository(newsDao, newsApiService)

                return NewsViewModel(repository) as T
            }
        }
    }
}