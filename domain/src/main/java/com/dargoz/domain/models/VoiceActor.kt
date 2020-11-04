package com.dargoz.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VoiceActor(
    val malId: Long,

    val name: String,

    val url: String,

    val imageUrl: String,

    val language: String,
): Parcelable