package com.dargoz.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val malId: Int,

    val type: String,

    val name: String,

    val url: String,
): Parcelable