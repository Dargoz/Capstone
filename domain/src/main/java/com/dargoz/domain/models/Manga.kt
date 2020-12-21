package com.dargoz.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Manga(
    val id: Long,
    val malId: Long,
    val rank: Long,
    val title: String,
    val url: String,
    val type: String,
    val volume: Int,
    val startDate: String,
    val members: Long,
    val score: Double,
    val imageUrl: String

): Parcelable