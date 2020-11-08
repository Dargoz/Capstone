package com.dargoz.domain.usecases

import com.dargoz.domain.models.Anime
import com.dargoz.domain.repositories.IDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(private val dataRepository: IDataRepository)
    : FavoriteUseCase {

    override fun getFavoriteAnimeList(): Flow<List<Anime>> {
        return dataRepository.getFavoriteAnimeList()
    }

}