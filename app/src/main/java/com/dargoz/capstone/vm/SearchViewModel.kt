package com.dargoz.capstone.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dargoz.domain.usecases.SearchUseCase

class SearchViewModel @ViewModelInject constructor(private val searchUseCase: SearchUseCase)
    : ViewModel() {

        fun searchAnime(queryString: String) = searchUseCase.searchFromText("anime", queryString).asLiveData()
}