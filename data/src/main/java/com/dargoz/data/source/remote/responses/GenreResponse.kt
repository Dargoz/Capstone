package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field:SerializedName("mal_id")
    val malId: Int,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String,
)