package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Characters
import kotlinx.coroutines.flow.Flow

interface AnimeCharactersAndStaffUseCase {

    fun getAnimeCharactersAndStaff(animeId: Long): Flow<Resource<List<Characters>?>>
}