package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {

    fun getCurrentSeasonAnimeList(): Flow<Resource<List<Anime>>>
    fun getTodayScheduleAnime(): Flow<Resource<List<Anime>>>
    fun getTopUpcomingAnime():Flow<Resource<List<Anime>>>
}