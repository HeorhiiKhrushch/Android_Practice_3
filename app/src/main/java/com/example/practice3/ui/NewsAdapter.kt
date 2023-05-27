package com.example.practice3.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice3.data.News
import com.example.practice3.databinding.ItemNewsBinding

class NewsAdapter(private val interact: NewsAdapterInteract) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setNews(news: List<News>) {
        newsList = news
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            with(binding) {
                title.text = news.title
                subtitle.text = news.text
                buttonStar.setImageResource(
                    if (news.isFavorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
                )

                buttonStar.setOnClickListener {
                    Log.d("NewsResult", "onStarButtonClick id: ${news.id}")
                    interact.onStarButtonClick(news)
                }

                Glide.with(preview.context)
                    .load(news.previewUrl)
                    .into(preview)

                itemView.setOnClickListener {
                    Log.d("NewsResult", "onNewsItemClick id: ${news.id}")
                    interact.onNewsItemClick(news)
                }
            }
        }
    }
}

interface NewsAdapterInteract {
    fun onNewsItemClick(news: News)
    fun onStarButtonClick(news: News)
}