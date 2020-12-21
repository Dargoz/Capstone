package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class ListAnimeResponse(
    @field:SerializedName("request_cached")
    val requestCached: Boolean,

    @field:SerializedName("request_cache_expiry")
    val requestCacheExpiry: Long,

    @field:SerializedName("results")
    val results: List<AnimeResponse>
)