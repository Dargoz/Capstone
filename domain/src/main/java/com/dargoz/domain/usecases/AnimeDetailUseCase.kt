package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeDetailUseCase {

    fun getDetailAnimeData(animeId: Long): Flow<Resource<Anime>>
}