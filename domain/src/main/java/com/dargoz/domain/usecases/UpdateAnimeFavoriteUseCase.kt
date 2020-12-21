package com.dargoz.domain.usecases

interface UpdateAnimeFavoriteUseCase {

    fun updateAnimeFavoriteFlag(animeId: Long, isFavorite: Boolean)
}