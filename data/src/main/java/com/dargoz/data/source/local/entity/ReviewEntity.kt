package com.dargoz.data.source.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dargoz.domain.models.Reviewer
import java.util.*

@Entity(tableName = "review", indices = [Index(value = ["malId"], unique = true)])
data class ReviewEntity(
    val malId: Long,

    val animeId: Long,

    val url: String,

    val type: String?,

    val helpfulCount: Long,

    val date: Date,

    val reviewer: Reviewer,

    val content: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)