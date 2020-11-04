package com.dargoz.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dargoz.domain.models.Characters
import com.dargoz.domain.models.Genre

@Entity(tableName = "anime", indices = [Index(value = ["malId"], unique = true)])
data class AnimeEntity constructor(
    val malId: Long,

    val title: String,

    val titleEnglish: String?,

    val titleJapanese: String?,

    val imageUrl: String,

    val synopsis: String,

    val type: String,

    val source: String?,

    val status: String?,

    val score: Double,

    val episodes: Int?,

    val duration: String?,

    val rating: String?,

    val popularity: Long?,

    val members: Long?,

    val genres: List<Genre>,

    val characters: List<Characters>?,

    val openingThemes: List<String>?,

    val endingThemes: List<String>?,

    val isFavorite: Boolean,

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
)