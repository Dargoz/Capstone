package com.dargoz.capstone.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dargoz.domain.models.Anime
import com.dargoz.domain.usecases.CurrentSeasonUseCase
import com.dargoz.domain.utils.SeasonNameUtil
import java.util.*

class HomeViewModel @ViewModelInject constructor(seasonUseCase: CurrentSeasonUseCase) : ViewModel() {

    val anime = seasonUseCase.getCurrentSeasonAnimeList().asLiveData()

    fun setCurrentSeasonTitle(): String {
        val calendar = Calendar.getInstance()
        return "${SeasonNameUtil.generateSeasonName(calendar)} ${calendar[Calendar.YEAR]} Anime"
    }


}