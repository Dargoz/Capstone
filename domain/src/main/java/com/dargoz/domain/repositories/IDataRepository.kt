package com.dargoz.domain.repositories

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import com.dargoz.domain.models.Characters
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    fun getCurrentSeasonAnimeList(year: Int, seasonName: String): Flow<Resource<List<Anime>>>
    fun getDetailAnime(animeId: Long): Flow<Resource<Anime>>
    fun getAnimeCharactersAndStaff(animeId: Long): Flow<Resource<List<Characters>?>>
}