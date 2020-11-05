package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName
import java.util.*

data class ReviewResponse(
    @field:SerializedName("mal_id")
    val malId: Long,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("type")
    val type: String?,

    @field:SerializedName("helpful_count")
    val helpfulCount: Long,

    @field:SerializedName("date")
    val date: Date,

    @field:SerializedName("reviewer")
    val reviewer: ReviewerResponse,

    @field:SerializedName("content")
    val content: String
)