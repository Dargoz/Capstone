package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @field:SerializedName("mal_id")
    val malId: Long,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("voice_actors")
    val voiceActors: List<VoiceActorResponse>

)