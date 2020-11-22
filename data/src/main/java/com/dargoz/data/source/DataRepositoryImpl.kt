package com.dargoz.data.source

import android.util.Log
import com.dargoz.data.source.local.LocalDataSource
import com.dargoz.data.source.remote.RemoteDataSource
import com.dargoz.data.source.remote.network.ApiResponse
import com.dargoz.data.source.remote.responses.*
import com.dargoz.data.utils.AppExecutors
import com.dargoz.data.utils.DataMapper
import com.dargoz.data.utils.DataMapper.mapTopAnimeResponseToAnimeResponse
import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import com.dargoz.domain.models.Characters
import com.dargoz.domain.models.Manga
import com.dargoz.domain.models.Review
import com.dargoz.domain.repositories.IDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IDataRepository {

    override fun getCurrentSeasonAnimeList(
        year: Int,
        seasonName: String
    ): Flow<Resource<List<Anime>>> =
        object : NetworkBoundResource<List<Anime>, List<AnimeResponse>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> =
                remoteDataSource.getSeasonAnimeList(year, seasonName)

            override suspend fun loadFromDB(): Flow<List<Anime>> =
                localDataSource.getAllAnimeOfSeason(year, seasonName)
                    .map { DataMapper.mapEntitiesToDomain(it) }

            override suspend fun shouldFetch(data: List<Anime>?): Boolean =
                data != null && data.isEmpty()

            override suspend fun saveCallResult(data: List<AnimeResponse>, cache: List<Anime>?) {
                localDataSource.insertAllAnime(
                    DataMapper.mapResponseToEntities(
                        data, cache,
                        seasonName = seasonName,
                        year = year
                    )
                )
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

            override suspend fun saveCallResult(
                data: List<CharacterResponse>,
                cache: List<Characters>?
            ) {
                localDataSource.updateAnimeCharactersByMalId(
                    animeId, DataMapper.mapResponseToModelChar(data)
                )
            }

        }.asFlow()

    override fun getAnimeReviews(animeId: Long): Flow<Resource<List<Review>>> =
        object : NetworkBoundResource<List<Review>, List<ReviewResponse>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<ReviewResponse>>> {
                return remoteDataSource.getAnimeReviews(animeId)
            }

            override suspend fun loadFromDB(): Flow<List<Review>> {
                return localDataSource.getAnimeReviewsById(animeId)
                    .map { DataMapper.mapEntitiesToDomainReview(it) }
            }

            override suspend fun shouldFetch(data: List<Review>?): Boolean =
                data == null || data.isEmpty()


            override suspend fun saveCallResult(data: List<ReviewResponse>, cache: List<Review>?) {
                localDataSource.insertAllReviews(
                    DataMapper.mapReviewResponseToEntities(animeId, data)
                )
            }

        }.asFlow()

    override fun getFavoriteAnimeList(): Flow<List<Anime>> {
        return localDataSource.getFavoriteAnimeList().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun updateAnimeFavorite(animeId: Long, isFavorite: Boolean) {
        appExecutors.diskIO()
            .execute { localDataSource.updateAnimeFavoriteFlag(animeId, isFavorite) }
    }

    override fun getScheduleAnime(day: String): Flow<Resource<List<Anime>>> =
        object : NetworkBoundResource<List<Anime>, List<AnimeResponse>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> {
                return remoteDataSource.getScheduleAnime(day)
            }

            override suspend fun loadFromDB(): Flow<List<Anime>> {
                return localDataSource.getAllTodayAnime(day)
                    .map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun shouldFetch(data: List<Anime>?): Boolean {
                return true
            }

            override suspend fun saveCallResult(data: List<AnimeResponse>, cache: List<Anime>?) {
                localDataSource.insertAllAnime(
                    DataMapper.mapResponseToEntities(data, cache, day = day)
                )
            }

        }.asFlow()

    override fun getTopList(type: String, page: Int, subtype: String): Flow<Resource<List<Anime>>> =
        object : NetworkBoundResource<List<Anime>, List<TopAnimeResponse>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<TopAnimeResponse>>> {
                return remoteDataSource.getTopList(type, page, subtype)
            }

            override suspend fun loadFromDB(): Flow<List<Anime>> {
                return localDataSource.getListAnimeOf(subtype)
                    .map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun shouldFetch(data: List<Anime>?): Boolean {
                return true
            }

            override suspend fun saveCallResult(data: List<TopAnimeResponse>, cache: List<Anime>?) {
                localDataSource.insertAllAnime(
                    DataMapper.mapResponseToEntities(
                        mapTopAnimeResponseToAnimeResponse(data),
                        cache,
                        subtype = subtype
                    )
                )
            }

        }.asFlow()

    override fun getTopMangaList(type: String, page: Int, subtype: String)
            : Flow<Resource<List<Manga>>> =
        object : NetworkBoundResource<List<Manga>, List<MangaResponse>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<MangaResponse>>> {
                return remoteDataSource.getMangaTopList(type, page, subtype)
            }

            override suspend fun loadFromDB(): Flow<List<Manga>> {
                return localDataSource.getAllManga(subtype)
                    .map { DataMapper.mapMangaEntitiesToDomain(it) }
            }

            override suspend fun shouldFetch(data: List<Manga>?): Boolean {
                return true
            }

            override suspend fun saveCallResult(data: List<MangaResponse>, cache: List<Manga>?) {
                localDataSource.insertAllManga(DataMapper.mapMangaResponseToEntities(data))
            }


        }.asFlow()


}