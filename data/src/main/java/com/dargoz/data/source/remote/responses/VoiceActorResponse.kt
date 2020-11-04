package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class VoiceActorResponse(
    @field:SerializedName("mal_id")
    val malId: Long,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("language")
    val language: String,
)