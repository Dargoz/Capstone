package com.dargoz.data.source

import com.dargoz.data.source.remote.network.ApiResponse
import com.dargoz.domain.Resource

import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if(shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when(val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data, dbSource)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}
    abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
    abstract suspend fun loadFromDB(): Flow<ResultType>
    abstract suspend fun shouldFetch(data: ResultType?): Boolean
    protected abstract suspend fun saveCallResult(data: RequestType, cache: ResultType? = null)

    fun asFlow(): Flow<Resource<ResultType>> = result
}