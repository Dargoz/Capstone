package com.dargoz.capstone.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dargoz.domain.usecases.MangaUseCase

class MangaViewModel @ViewModelInject constructor(mangaUseCase: MangaUseCase)
    : ViewModel() {

    val topManga = mangaUseCase.getTopManga().asLiveData()
    val topManhwa = mangaUseCase.getTopManhwa().asLiveData()
}