package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Characters
import com.dargoz.domain.repositories.IDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeCharactersAndStaffInteractor @Inject constructor(
    private val dataRepository: IDataRepository): AnimeCharactersAndStaffUseCase {

    override fun getAnimeCharactersAndStaff(animeId: Long): Flow<Resource<List<Characters>?>> =
        dataRepository.getAnimeCharactersAndStaff(animeId)

}