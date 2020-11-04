package com.dargoz.capstone.utils

import com.dargoz.domain.models.Anime

interface AnimeObjectListener {

    fun onDataReceived(anime: Anime)
}