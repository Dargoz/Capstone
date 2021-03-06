package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName


data class TopAnimeResponse(

    @field:SerializedName("mal_id")
    val malId: Long,

    @field:SerializedName("rank")
    val rank: Long,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("start_date")
    val startDate: String,

    @field:SerializedName("members")
    val members: Long,

    @field:SerializedName("score")
    val score: Double
)