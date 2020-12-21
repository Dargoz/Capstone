package com.dargoz.domain.usecases

import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import com.dargoz.domain.repositories.IDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchInteractor
@Inject constructor(private val dataRepository: IDataRepository) : SearchUseCase {

    override fun searchFromText(data: String, queryString: String): Flow<Resource<List<Anime>>> {
        return dataRepository.getSearchData(data, queryString, 1)
    }

}