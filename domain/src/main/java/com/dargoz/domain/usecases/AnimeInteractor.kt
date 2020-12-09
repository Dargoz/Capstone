package com.dargoz.domain.usecases

import android.util.Log
import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import com.dargoz.domain.repositories.IDataRepository
import com.dargoz.domain.utils.SeasonNameUtil
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AnimeInteractor @Inject constructor(private val dataRepository: IDataRepository) :
    AnimeUseCase {

    override fun getCurrentSeasonAnimeList(): Flow<Resource<List<Anime>>> {
        val seasonName = SeasonNameUtil.generateSeasonName(Calendar.getInstance())
            .toLowerCase(Locale.ROOT)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        return dataRepository.getCurrentSeasonAnimeList(
            if (seasonName == "winter") year + 1 else year,
            seasonName
        )
    }


    override fun getTodayScheduleAnime(): Flow<Resource<List<Anime>>> {
        val simpleDateFormat = SimpleDateFormat("EEEE", Locale.US)
        val date = Date()
        val dayOfWeek = simpleDateFormat.format(date)
        Log.i("DRG", "Today is : $dayOfWeek")
        return dataRepository.getScheduleAnime(dayOfWeek.toLowerCase(Locale.US))
    }

    override fun getTopUpcomingAnime(): Flow<Resource<List<Anime>>> {
        return dataRepository.getTopList("anime", 1, "upcoming")
    }
}