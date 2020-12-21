package com.dargoz.capstone.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.dargoz.capstone.R
import com.dargoz.capstone.databinding.ReviewsFragmentBinding
import com.dargoz.capstone.ui.adapters.ReviewListAdapter
import com.dargoz.capstone.utils.ActivityHelper
import com.dargoz.capstone.vm.ReviewsViewModel
import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewsFragment : Fragment() {

    companion object {
        fun newInstance() = ReviewsFragment()
    }

    private var _binding : ReviewsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReviewsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ReviewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = ReviewListAdapter()
        binding.animeReviewRcView.adapter = adapter
        val anime: Anime = ActivityHelper.getAnimeResource(this)
        viewModel.animeReviews(anime.malId).observe(viewLifecycleOwner, { reviews ->
            when(reviews) {
                is Resource.Loading -> Log.i("DRG","Loading Review data..")
                is Resource.Success -> {
                    val reviewList = reviews.data
                    if(reviewList != null) {
                        adapter.reviewList = reviewList
                        adapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error ->
                    Toast.makeText(requireContext(),
                        getString(R.string.resource_error_text), Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}