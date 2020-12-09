package com.dargoz.capstone.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dargoz.domain.usecases.UpdateAnimeFavoriteUseCase

class DetailViewModel @ViewModelInject
constructor(private val updateAnimeFavoriteUseCase: UpdateAnimeFavoriteUseCase) : ViewModel() {

    fun updateAnimeFavoriteFlag(animeId: Long, isFavorite: Boolean) = updateAnimeFavoriteUseCase.updateAnimeFavoriteFlag(animeId, isFavorite)
}