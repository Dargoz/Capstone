package com.dargoz.data.source.remote

import android.util.Log
import com.dargoz.data.source.remote.network.ApiResponse
import com.dargoz.data.source.remote.network.ApiService
import com.dargoz.data.source.remote.responses.*
import com.dargoz.data.utils.ResponseHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getSeasonAnimeList(
        year: Int,
        seasonName: String
    ): Flow<ApiResponse<List<AnimeResponse>>> {
        return flow {
            try {
                val response = apiService.getSeasonList(year, seasonName)
                Log.w("DRG", "season : ${response.seasonName} ${response.seasonYear}")
                Log.w("DRG", "list : ${response.animeList}")
                val data = response.animeList

                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.animeList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.w("DRG", "${this.javaClass.simpleName} :: ${e.message}")
            }

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailAnime(animeId: Long): Flow<ApiResponse<AnimeResponse>> {
        return flow {
            try {
                val response = apiService.getAnime(animeId)
                Log.w("DRG", "detail anime : ${response.titleEnglish} ${response.titleJapanese}")
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.w("DRG", "${this.javaClass.simpleName} :: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAnimeCharactersAndStaff(animeId: Long): Flow<ApiResponse<List<CharacterResponse>>> {
        return flow {
            try {
                val response = apiService.getAnimeCharactersAndStaff(animeId)
                Log.d("DRG", "characters : ${response.characters}")
                val data = response.characters
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.characters))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.w("DRG", "${this.javaClass.simpleName} :: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAnimeReviews(animeId: Long): Flow<ApiResponse<List<ReviewResponse>>> =
        flow {
            try {
                val response = apiService.getAnimeReviews(animeId)
                val data = response.reviews
                Log.d("DRG", "characters : $data")
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.w("DRG", "${this.javaClass.simpleName} :: ${e.message}")
            }

        }.flowOn(Dispatchers.IO)

    suspend fun getScheduleAnime(day: String): Flow<ApiResponse<List<AnimeResponse>>> =
        flow {
            try {
                val response = apiService.getAnimeSchedule(day)
                val data = ResponseHelper.getTodayField(day, response)
                Log.d("DRG", "$day data : $data")
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.w("DRG", "${this.javaClass.simpleName} :: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)

    fun getTopList(type: String, page: Int, subtype: String)
            : Flow<ApiResponse<List<TopAnimeResponse>>> = flow {
        try {
            val response = apiService.getTopList(type, page, subtype)
            val data = response.top
            Log.d("DRG", "$type $subtype data : $data")
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.w("DRG", "${this.javaClass.simpleName} :: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)

    fun getMangaTopList(type: String, page: Int, subtype: String)
            : Flow<ApiResponse<List<MangaResponse>>> = flow {
        try {
            Log.d("DRG", "$type $subtype")
            val response = apiService.getTopMangaList(type, page, subtype)
            Log.d("DRG", "response $type $subtype")
            val data = response.top
            Log.d("DRG", "$type $subtype data : $data")
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.toString()))
            Log.w("DRG", "${this.javaClass.simpleName} :: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)

    fun getSearch(type: String, queryString: String, pageNumber: Int)
    : Flow<ApiResponse<List<AnimeResponse>>> = flow {
        try {
            val response = apiService.search(type, queryString, pageNumber)
            Log.d("DRG", "response $type ${response.results}")
            val data = response.results
            if(data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.Error(e.toString()))
            Log.w("DRG", "${this.javaClass.simpleName} :: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)

}