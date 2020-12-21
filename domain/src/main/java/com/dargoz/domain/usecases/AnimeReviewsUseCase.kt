package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Review
import kotlinx.coroutines.flow.Flow


interface AnimeReviewsUseCase {

    fun getAnimeReviews(animeId: Long): Flow<Resource<List<Review>>>
}