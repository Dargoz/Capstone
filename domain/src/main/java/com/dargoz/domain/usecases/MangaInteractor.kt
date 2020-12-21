package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Manga
import com.dargoz.domain.repositories.IDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaInteractor @Inject constructor(private val dataRepository: IDataRepository) : MangaUseCase {
    override fun getTopManga(): Flow<Resource<List<Manga>>> {
        return dataRepository.getTopMangaList("manga", 1, "manga")
    }

    override fun getTopManhwa(): Flow<Resource<List<Manga>>> {
        return dataRepository.getTopMangaList("manga", 1, "manhwa")
    }

}