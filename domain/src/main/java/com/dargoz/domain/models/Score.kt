package com.dargoz.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Score(
    val overall: Int,

    val story: Int,

    val animation: Int,

    val sound: Int,

    val character: Int,

    val enjoyment: Int,
) : Parcelable