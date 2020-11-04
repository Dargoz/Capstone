package com.dargoz.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Anime(
    val id: Long,

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

    val isFavorite: Boolean = false,

) : Parcelable