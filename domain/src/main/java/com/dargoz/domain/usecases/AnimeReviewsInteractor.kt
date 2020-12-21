package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Review
import com.dargoz.domain.repositories.IDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeReviewsInteractor @Inject constructor(private val dataRepository: IDataRepository)
    : AnimeReviewsUseCase {

    override fun getAnimeReviews(animeId: Long): Flow<Resource<List<Review>>> {
        return dataRepository.getAnimeReviews(animeId)
    }
}