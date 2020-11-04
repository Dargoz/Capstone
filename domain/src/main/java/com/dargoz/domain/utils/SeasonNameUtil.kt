package com.dargoz.domain.utils

import java.util.*

object SeasonNameUtil {

    fun generateSeasonName(calendar: Calendar) : String {
        when {
            calendar[Calendar.MONTH] == Calendar.JANUARY -> {
                return "Winter"
            }
            calendar[Calendar.MONTH] == Calendar.FEBRUARY -> {
                return "Winter"
            }
            calendar[Calendar.MONTH] == Calendar.MARCH -> {
                return "Spring"
            }
            calendar[Calendar.MONTH] == Calendar.APRIL -> {
                return "Spring"
            }
            calendar[Calendar.MONTH] == Calendar.MAY -> {
                return "Summer"
            }
            calendar[Calendar.MONTH] == Calendar.JUNE -> {
                return "Summer"
            }
            calendar[Calendar.MONTH] == Calendar.JULY -> {
                return "Summer"
            }
            calendar[Calendar.MONTH] == Calendar.AUGUST -> {
                return "Summer"
            }
            calendar[Calendar.MONTH] == Calendar.SEPTEMBER -> {
                return "Fall"
            }
            calendar[Calendar.MONTH] == Calendar.OCTOBER -> {
                return "Fall"
            }
            calendar[Calendar.MONTH] == Calendar.NOVEMBER -> {
                return "Fall"
            }
            calendar[Calendar.MONTH] == Calendar.DECEMBER -> {
                return "Winter"
            }
            else -> {
                return "No Season"
            }
        }
    }
}