package com.dargoz.capstone.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dargoz.domain.usecases.AnimeCharactersAndStaffUseCase

class CharactersViewModel @ViewModelInject constructor(
    private val animeCharactersAndStaffUseCase: AnimeCharactersAndStaffUseCase) : ViewModel() {

    fun animeCharactersAndStaff(animeId: Long) =
        animeCharactersAndStaffUseCase.getAnimeCharactersAndStaff(animeId).asLiveData()
}