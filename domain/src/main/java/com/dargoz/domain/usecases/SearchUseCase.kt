package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {

    fun searchFromText(data: String, queryString: String): Flow<Resource<List<Anime>>>

}