package com.dargoz.data.source.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "manga", indices = [Index(value = ["malId"], unique = true)])
data class MangaEntity(
    val malId: Long,

    val rank: Long,

    val title: String,

    val url: String,

    val type: String,

    val volume: Int,

    val startDate: String,

    val members: Long,

    val score: Double,

    val imageUrl: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)