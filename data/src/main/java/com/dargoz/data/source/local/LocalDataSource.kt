package com.dargoz.data.source.local

import com.dargoz.data.source.local.entity.AnimeEntity
import com.dargoz.data.source.local.room.AnimeDao
import com.dargoz.domain.models.Characters
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val animeDao: AnimeDao) {

    fun getAllAnimeOfSeason(): Flow<List<AnimeEntity>> = animeDao.getAllAnime()

    fun getAnimeByMalId(malId : Long): Flow<AnimeEntity> = animeDao.getAnimeByMalId(malId)

    suspend fun insertAllAnime(animeEntities: List<AnimeEntity>) = animeDao.insertAllAnime(animeEntities)

    suspend fun updateAnime(animeEntity: AnimeEntity) = animeDao.updateAnime(animeEntity)

    suspend fun updateAnimeCharactersByMalId(animeId: Long, characterList : List<Characters>)
            = animeDao.updateAnimeCharacters(animeId, characterList)

}