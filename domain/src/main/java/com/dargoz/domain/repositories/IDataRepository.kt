package com.dargoz.domain.repositories

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import com.dargoz.domain.models.Characters
import com.dargoz.domain.models.Review
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    fun getCurrentSeasonAnimeList(year: Int, seasonName: String): Flow<Resource<List<Anime>>>
    fun getDetailAnime(animeId: Long): Flow<Resource<Anime>>
    fun getAnimeCharactersAndStaff(animeId: Long): Flow<Resource<List<Characters>?>>
    fun getAnimeReviews(animeId: Long): Flow<Resource<List<Review>>>
    fun getFavoriteAnimeList(): Flow<List<Anime>>
    fun updateAnimeFavorite(animeId: Long, isFavorite: Boolean)
    fun getScheduleAnime(day: String): Flow<Resource<List<Anime>>>
    fun getTopList(type: String, page: Int, subtype: String): Flow<Resource<List<Anime>>>
}