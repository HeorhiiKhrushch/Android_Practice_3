package com.example.practice3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.practice3.data.News
import com.example.practice3.databinding.FragmentNewsDetailsBinding
import com.example.practice3.view_model.NewsViewModel
import kotlinx.coroutines.launch

class FragmentNewsDetails : Fragment() {

    private val viewModel: NewsViewModel by activityViewModels { NewsViewModel.Factory }

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)

        subscribeToNewsChanges()

        return binding.root
    }

    private fun subscribeToNewsChanges() {
        val newsId: Int = arguments?.getInt(ARG_NEWS_ID) ?: -1

        lifecycleScope.launch {
            viewModel.getNewsByIdFlow(newsId).collect {
                updateNewsUi(it)
            }
        }
    }

    private fun updateNewsUi(news: News) {
        with(binding) {
            title.text = news.title
            subtitle.text = news.text
            buttonStar.setImageResource(
                if (news.isFavorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
            )

            buttonStar.setOnClickListener {
                viewModel.toggleFavoriteStatus(news)
            }

            Glide.with(requireContext())
                .load(news.fullUrl)
                .into(preview)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_NEWS_ID = "news_id"

        fun newInstance(newsId: Int): FragmentNewsDetails {
            val fragment = FragmentNewsDetails()
            val args = bundleOf(ARG_NEWS_ID to newsId)
            fragment.arguments = args
            return fragment
        }
    }
}