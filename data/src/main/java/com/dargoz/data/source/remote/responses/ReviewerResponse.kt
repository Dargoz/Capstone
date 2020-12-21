package com.dargoz.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class ReviewerResponse(
    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("episodes_seen")
    val episodeSeen: Int,

    @field:SerializedName("scores")
    val scores: ScoreResponse
)