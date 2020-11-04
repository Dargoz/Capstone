package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class ListSeasonAnimeResponse (
    @field:SerializedName("season_name")
    val seasonName: String,

    @field:SerializedName("season_year")
    val seasonYear: String,

    @field:SerializedName("anime")
    val animeList: List<AnimeResponse>

)