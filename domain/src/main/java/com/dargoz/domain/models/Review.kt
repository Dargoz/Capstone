package com.dargoz.domain.models

import java.util.*

data class Review(
    val id: Long,

    val malId: Long,

    val animeId: Long,

    val url: String,

    val type: String?,

    val helpfulCount: Long,

    val date: Date,

    val reviewer: Reviewer,

    val content: String
)