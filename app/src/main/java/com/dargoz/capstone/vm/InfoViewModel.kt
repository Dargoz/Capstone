package com.dargoz.capstone.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dargoz.domain.usecases.AnimeDetailUseCase

class InfoViewModel @ViewModelInject constructor(private val animeDetailUseCase: AnimeDetailUseCase)
    : ViewModel() {

    fun animeData(animeId: Long) = animeDetailUseCase.getDetailAnimeData(animeId).asLiveData()
}