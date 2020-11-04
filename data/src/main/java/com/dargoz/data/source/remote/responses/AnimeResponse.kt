package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class AnimeResponse (
    @field:SerializedName("mal_id")
    val id: Long,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("title_english")
    val titleEnglish: String,

    @field:SerializedName("title_japanese")
    val titleJapanese: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("synopsis")
    val synopsis: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("source")
    val source: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("score")
    val score: Double,

    @field:SerializedName("episodes")
    val episodes: Int,

    @field:SerializedName("duration")
    val duration: String,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("popularity")
    val popularity: Long,

    @field:SerializedName("members")
    val members: Long,

    @field:SerializedName("genres")
    val genres: List<GenreResponse>,

    @field:SerializedName("opening_themes")
    val openingThemes: List<String>,

    @field:SerializedName("ending_themes")
    val endingThemes: List<String>
)