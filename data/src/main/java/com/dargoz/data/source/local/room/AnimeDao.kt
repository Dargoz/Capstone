package com.dargoz.data.source.local.room

import androidx.room.*
import com.dargoz.data.source.local.entity.AnimeEntity
import com.dargoz.domain.models.Characters
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(animeEntities: List<AnimeEntity>)

    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE malId=:malId")
    fun getAnimeByMalId(malId: Long): Flow<AnimeEntity>

    @Update
    suspend fun updateAnime(animeEntity: AnimeEntity)

    @Query("SELECT * FROM anime WHERE isFavorite = 1")
    fun getFavoriteAnime(): Flow<List<AnimeEntity>>

    @Query("UPDATE anime SET characters=:characters WHERE malId=:malId")
    suspend fun updateAnimeCharacters(malId: Long, characters: List<Characters>)

    @Query("UPDATE anime SET isFavorite=:isFavorite WHERE malId=:malId")
    fun updateAnimeFavorite(malId: Long, isFavorite: Boolean)

}