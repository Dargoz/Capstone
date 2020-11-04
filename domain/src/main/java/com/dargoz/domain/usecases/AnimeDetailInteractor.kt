package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import com.dargoz.domain.repositories.IDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeDetailInteractor @Inject constructor(private val dataRepository: IDataRepository)
    : AnimeDetailUseCase {

    override fun getDetailAnimeData(animeId: Long): Flow<Resource<Anime>> {
        return dataRepository.getDetailAnime(animeId)
    }
}