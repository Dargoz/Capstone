package com.dargoz.data.source.remote.network

import com.dargoz.data.source.remote.responses.*
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("season/{year}/{seasonName}")
    suspend fun getSeasonList(
        @Path("year") year: Int,
        @Path("seasonName") seasonName: String
    ): ListSeasonAnimeResponse

    @GET("anime/{mal_id}")
    suspend fun getAnime(@Path("mal_id") id: Long): AnimeResponse

    @GET("anime/{mal_id}/reviews")
    suspend fun getAnimeReviews(@Path("mal_id") id: Long): ListReviewResponse

    @GET("anime/{mal_id}/characters_staff")
    suspend fun getAnimeCharactersAndStaff(@Path("mal_id") id: Long): ListCharacterResponse

    @GET("schedule/{day}")
    suspend fun getAnimeSchedule(@Path("day") day: String): ListScheduleResponse

    @GET("top/{type}/{page}/{subtype}")
    suspend fun getTopList(
        @Path("type") type: String, @Path("page") page: Int,
        @Path("subtype") subtype: String
    ): ListTopResponse

    @GET("top/{type}/{page}/{subtype}")
    suspend fun getTopMangaList(
        @Path("type") type: String, @Path("page") page: Int,
        @Path("subtype") subtype: String
    ): ListTopMangaResponse
}