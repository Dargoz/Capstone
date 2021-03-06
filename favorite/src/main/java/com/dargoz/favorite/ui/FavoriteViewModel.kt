package com.dargoz.favorite.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dargoz.capstone.di.UseCaseFavoriteQualifier
import com.dargoz.domain.usecases.FavoriteUseCase

class FavoriteViewModel @ViewModelInject constructor(
    @UseCaseFavoriteQualifier private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    val favoriteAnimeList = favoriteUseCase.getFavoriteAnimeList().asLiveData()
}