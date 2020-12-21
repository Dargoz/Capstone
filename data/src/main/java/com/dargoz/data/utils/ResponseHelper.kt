package com.dargoz.data.utils

import com.dargoz.data.source.remote.responses.AnimeResponse
import com.dargoz.data.source.remote.responses.ListScheduleResponse
import java.lang.IllegalArgumentException

object ResponseHelper {

    fun getTodayField(day: String, response: ListScheduleResponse): List<AnimeResponse> {
        return when(day) {
            "monday" -> response.monday
            "tuesday" -> response.tuesday
            "wednesday" -> response.wednesday
            "thursday" -> response.thursday
            "friday" -> response.friday
            "saturday" -> response.saturday
            "sunday" -> response.sunday
            else -> throw IllegalArgumentException("unknown day with value $day")
        }
    }

}