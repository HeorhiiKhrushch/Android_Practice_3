package com.example.practice3.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice3.data.News
import com.example.practice3.databinding.FragmentNewsBinding
import com.example.practice3.view_model.NewsViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class FragmentNews : Fragment(), NewsAdapterInteract {

    private val viewModel: NewsViewModel by activityViewModels { NewsViewModel.Factory }

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        initRecyclerView()
        subscribeToNewsUpdates()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter(this)
        binding.recyclerView.adapter = newsAdapter
    }

    private fun subscribeToNewsUpdates() {
        lifecycleScope.launch {
            viewModel.allNews.filter { it.isNotEmpty() }.collect { news ->
                Log.d("NewsResult", "subscribeToNewsUpdates size: ${news.size}")

                binding.recyclerView.isVisible = true
                binding.progress.isVisible = false
                newsAdapter.setNews(news)
            }
        }
    }

    override fun onNewsItemClick(news: News) {
        val newsDetailsFragment = FragmentNewsDetails.newInstance(news.id)
        requireActivity().addFragment(newsDetailsFragment)
    }

    override fun onStarButtonClick(news: News) {
        viewModel.toggleFavoriteStatus(news)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}