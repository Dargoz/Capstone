package com.dargoz.data.source.remote.responses


import com.google.gson.annotations.SerializedName

data class ListScheduleResponse(
    @field:SerializedName("request_cached")
    val requestCached: Boolean,

    @field:SerializedName("request_cache_expiry")
    val requestCacheExpiry: Long,

    @field:SerializedName("monday")
    val monday: List<AnimeResponse>,

    @field:SerializedName("tuesday")
    val tuesday: List<AnimeResponse>,

    @field:SerializedName("wednesday")
    val wednesday: List<AnimeResponse>,

    @field:SerializedName("thursday")
    val thursday: List<AnimeResponse>,

    @field:SerializedName("friday")
    val friday: List<AnimeResponse>,

    @field:SerializedName("saturday")
    val saturday: List<AnimeResponse>,

    @field:SerializedName("sunday")
    val sunday: List<AnimeResponse>



)