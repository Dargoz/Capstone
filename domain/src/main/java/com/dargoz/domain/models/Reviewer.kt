package com.dargoz.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reviewer(
    val url: String,

    val imageUrl: String,

    val username: String,

    val episodeSeen: Int,

    val scores: Score
) : Parcelable