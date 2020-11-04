package com.dargoz.domain.usecases

import com.dargoz.domain.repositories.IDataRepository
import com.dargoz.domain.utils.SeasonNameUtil
import java.util.*
import javax.inject.Inject

class CurrentSeasonInteractor @Inject constructor(private val dataRepository: IDataRepository) :
    CurrentSeasonUseCase {

    override fun getCurrentSeasonAnimeList() = dataRepository.getCurrentSeasonAnimeList(
        Calendar.getInstance().get(Calendar.YEAR),
        SeasonNameUtil.generateSeasonName(Calendar.getInstance()).toLowerCase(Locale.ROOT)
    )
}