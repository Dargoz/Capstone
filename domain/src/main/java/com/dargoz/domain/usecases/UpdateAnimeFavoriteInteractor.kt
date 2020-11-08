package com.dargoz.domain.usecases

import com.dargoz.domain.repositories.IDataRepository
import javax.inject.Inject

class UpdateAnimeFavoriteInteractor @Inject constructor(private val dataRepository: IDataRepository)
    : UpdateAnimeFavoriteUseCase {

    override fun updateAnimeFavoriteFlag(animeId: Long, isFavorite: Boolean) {
        dataRepository.updateAnimeFavorite(animeId, isFavorite)
    }
}