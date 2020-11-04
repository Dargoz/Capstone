package com.dargoz.data.source

import android.util.Log
import com.dargoz.data.source.local.LocalDataSource
import com.dargoz.data.source.remote.RemoteDataSource
import com.dargoz.data.source.remote.network.ApiResponse
import com.dargoz.data.source.remote.responses.AnimeResponse
import com.dargoz.data.source.remote.responses.CharacterResponse
import com.dargoz.data.utils.DataMapper
import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import com.dargoz.domain.models.Characters
import com.dargoz.domain.repositories.IDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
) : IDataRepository {

    override fun getCurrentSeasonAnimeList(
        year: Int,
        seasonName: String
    ): Flow<Resource<List<Anime>>> =
        object : NetworkBoundResource<List<Anime>, List<AnimeResponse>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> =
                remoteDataSource.getSeasonAnimeList(year, seasonName)

            override suspend fun loadFromDB(): Flow<List<Anime>> =
                localDataSource.getAllAnimeOfSeason()
                    .map { DataMapper.mapEntitiesToDomain(it) }

            override suspend fun shouldFetch(data: List<Anime>?): Boolean = true

            override suspend fun saveCallResult(data: List<AnimeResponse>, cache: List<Anime>?) {
                localDataSource.insertAllAnime(DataMapper.mapResponseToEntities(data))
            }


        }.asFlow()

    override fun getDetailAnime(animeId: Long): Flow<Resource<Anime>> =
        object : NetworkBoundResource<Anime, AnimeResponse>() {
            override suspend fun createCall(): Flow<ApiResponse<AnimeResponse>> {
                return remoteDataSource.getDetailAnime(animeId)
            }

            override suspend fun loadFromDB(): Flow<Anime> {
                return localDataSource.getAnimeByMalId(animeId)
                    .map { DataMapper.mapEntityToDomain(it) }
            }

            override suspend fun shouldFetch(data: Anime?): Boolean {
                return if (data != null) {
                    Log.v("DRG", "opening theme : ${data.openingThemes}")
                    data.openingThemes == null
                } else {
                    true
                }
            }

            override suspend fun saveCallResult(data: AnimeResponse, cache: Anime?) {
                Log.v("DRG", "save call result : $data ::: ${cache!!.id}")
                localDataSource.updateAnime(DataMapper.mapResponseToEntity(data, cache.id))
            }

        }.asFlow()

    override fun getAnimeCharactersAndStaff(animeId: Long): Flow<Resource<List<Characters>?>> =
        object : NetworkBoundResource<List<Characters>?, List<CharacterResponse>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<CharacterResponse>>> {
                return remoteDataSource.getAnimeCharactersAndStaff(animeId)
            }

            override suspend fun loadFromDB(): Flow<List<Characters>?> {
                return localDataSource.getAnimeByMalId(animeId).map {
                    DataMapper.mapEntitiesToCharactersDomain(it)
                }
            }

            override suspend fun shouldFetch(data: List<Characters>?): Boolean {
                Log.v("DRG", "opening theme : $data")
                return data == null
            }

            override suspend fun saveCallResult(data: List<CharacterResponse>,
                                                cache: List<Characters>?) {
                localDataSource.updateAnimeCharactersByMalId(
                    animeId, DataMapper.mapResponseToModelChar(data))
            }

        }.asFlow()
}