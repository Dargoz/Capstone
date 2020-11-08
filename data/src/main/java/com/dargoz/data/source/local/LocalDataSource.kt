package com.dargoz.data.source.local

import com.dargoz.data.source.local.entity.AnimeEntity
import com.dargoz.data.source.local.entity.ReviewEntity
import com.dargoz.data.source.local.room.AnimeDao
import com.dargoz.data.source.local.room.ReviewDao
import com.dargoz.domain.models.Characters
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val animeDao: AnimeDao,
                                          private val reviewDao: ReviewDao) {

    fun getAllAnimeOfSeason(): Flow<List<AnimeEntity>> = animeDao.getAllAnime()

    fun getAnimeByMalId(malId : Long): Flow<AnimeEntity> = animeDao.getAnimeByMalId(malId)

    fun getAnimeReviewsById(malId: Long): Flow<List<ReviewEntity>> = reviewDao.getReviewsByAnimeId(malId)

    fun getFavoriteAnimeList(): Flow<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    suspend fun insertAllAnime(animeEntities: List<AnimeEntity>) = animeDao.insertAllAnime(animeEntities)

    suspend fun updateAnime(animeEntity: AnimeEntity) = animeDao.updateAnime(animeEntity)

    suspend fun updateAnimeCharactersByMalId(animeId: Long, characterList : List<Characters>)
            = animeDao.updateAnimeCharacters(animeId, characterList)

    suspend fun insertAllReviews(reviewEntities: List<ReviewEntity>) = reviewDao.insertAllReviews(reviewEntities)

    fun updateAnimeFavoriteFlag(animeId: Long, isFavorite: Boolean) = animeDao.updateAnimeFavorite(animeId, isFavorite)

}