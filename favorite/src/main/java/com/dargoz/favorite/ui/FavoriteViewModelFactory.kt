package com.dargoz.favorite.ui

import androidx.lifecycle.SavedStateHandle
import com.dargoz.domain.usecases.FavoriteUseCase
import com.dargoz.favorite.di.ViewModelFactory
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(private val favoriteUseCase: FavoriteUseCase):
    ViewModelFactory<FavoriteViewModel> {

    override fun create(handle: SavedStateHandle): FavoriteViewModel {
        return FavoriteViewModel(handle, favoriteUseCase)
    }


}