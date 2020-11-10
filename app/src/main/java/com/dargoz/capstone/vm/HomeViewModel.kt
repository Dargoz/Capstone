package com.dargoz.capstone.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dargoz.domain.usecases.AnimeUseCase
import com.dargoz.domain.utils.SeasonNameUtil
import java.util.*

class HomeViewModel @ViewModelInject constructor(animeUseCase: AnimeUseCase) : ViewModel() {

    val anime = animeUseCase.getCurrentSeasonAnimeList().asLiveData()

    val todayAnimeSchedule = animeUseCase.getTodayScheduleAnime().asLiveData()

    val topUpcomingAnime = animeUseCase.getTopUpcomingAnime().asLiveData()

    fun setCurrentSeasonTitle(): String {
        val calendar = Calendar.getInstance()
        return "${SeasonNameUtil.generateSeasonName(calendar)} ${calendar[Calendar.YEAR]} Anime"
    }


}