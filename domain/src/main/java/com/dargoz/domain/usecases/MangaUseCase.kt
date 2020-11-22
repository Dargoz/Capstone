package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Manga
import kotlinx.coroutines.flow.Flow

interface MangaUseCase {

    fun getTopManga(): Flow<Resource<List<Manga>>>

    fun getTopManhwa(): Flow<Resource<List<Manga>>>
}