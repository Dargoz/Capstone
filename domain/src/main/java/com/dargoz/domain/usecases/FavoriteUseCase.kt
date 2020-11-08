package com.dargoz.domain.usecases

import com.dargoz.domain.models.Anime
import kotlinx.coroutines.flow.Flow


interface FavoriteUseCase {

    fun getFavoriteAnimeList(): Flow<List<Anime>>
}