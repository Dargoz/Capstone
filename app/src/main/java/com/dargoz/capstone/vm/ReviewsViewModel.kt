package com.dargoz.capstone.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dargoz.domain.usecases.AnimeReviewsUseCase

class ReviewsViewModel @ViewModelInject constructor(
    private val animeReviewsUseCase: AnimeReviewsUseCase) : ViewModel() {

    fun animeReviews(animeMalId: Long) = animeReviewsUseCase.getAnimeReviews(animeMalId).asLiveData()
}