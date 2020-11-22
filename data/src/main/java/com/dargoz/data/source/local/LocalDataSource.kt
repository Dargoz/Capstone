package com.dargoz.data.source.local

import com.dargoz.data.source.local.entity.AnimeEntity
import com.dargoz.data.source.local.entity.MangaEntity
import com.dargoz.data.source.local.entity.ReviewEntity
import com.dargoz.data.source.local.room.AnimeDao
import com.dargoz.data.source.local.room.MangaDao
import com.dargoz.data.source.local.room.ReviewDao
import com.dargoz.domain.models.Characters
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val animeDao: AnimeDao,
                                          private val reviewDao: ReviewDao,
                                          private val mangaDao: MangaDao) {

    fun getAllAnimeOfSeason(year: Int, seasonName: String): Flow<List<AnimeEntity>> = animeDao.getAllAnimeSeason(year, seasonName)

    fun getAnimeByMalId(malId : Long): Flow<AnimeEntity> = animeDao.getAnimeByMalId(malId)

    fun getAllTodayAnime(day: String): Flow<List<AnimeEntity>> = animeDao.getAllTodayAnime(day)

    fun getListAnimeOf(subtype: String): Flow<List<AnimeEntity>> = animeDao.getListAnimeOf(subtype)

    fun getAnimeReviewsById(malId: Long): Flow<List<ReviewEntity>> = reviewDao.getReviewsByAnimeId(malId)

    fun getFavoriteAnimeList(): Flow<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    suspend fun insertAllAnime(animeEntities: List<AnimeEntity>) = animeDao.insertAllAnime(animeEntities)

    suspend fun updateAnime(animeEntity: AnimeEntity) = animeDao.updateAnime(animeEntity)

    suspend fun updateAnimeCharactersByMalId(animeId: Long, characterList : List<Characters>)
            = animeDao.updateAnimeCharacters(animeId, characterList)

    suspend fun insertAllReviews(reviewEntities: List<ReviewEntity>) = reviewDao.insertAllReviews(reviewEntities)

    suspend fun insertAllManga(mangaEntities: List<MangaEntity>) = mangaDao.insertAllManga(mangaEntities)

    fun getAllManga(type: String) = mangaDao.getAllManga(type)

    fun updateAnimeFavoriteFlag(animeId: Long, isFavorite: Boolean) = animeDao.updateAnimeFavorite(animeId, isFavorite)

}