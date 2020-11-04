package com.dargoz.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Characters(
    val malId: Long,

    val url: String,

    val imageUrl: String,

    val name: String,

    val role: String,

    val voiceActors: List<VoiceActor>
): Parcelable