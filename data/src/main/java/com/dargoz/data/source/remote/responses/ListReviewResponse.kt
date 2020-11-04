package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class ListReviewResponse(
    @field:SerializedName("request_cached")
    val requestCached: Boolean,

    @field:SerializedName("request_cache_expiry")
    val requestCacheExpiry: Long,

    @field:SerializedName("reviews")
    val reviews: List<ReviewResponse>
)