package com.dargoz.data.utils

import androidx.room.TypeConverter
import com.dargoz.domain.models.Characters
import com.dargoz.domain.models.Genre
import com.dargoz.domain.models.Reviewer
import com.dargoz.domain.models.Score
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class DataConverters {

    @TypeConverter
    fun fromListGenreToJson(genres: List<Genre>?): String = if(genres == null) "" else Gson().toJson(genres)

    @TypeConverter
    fun fromJsonToListGenre(json: String): List<Genre>? = Gson().fromJson(json,
        object : TypeToken<List<Genre>>() {}.type
    )

    @TypeConverter
    fun fromListCharacterToJson(characters: List<Characters>?): String? = Gson().toJson(characters)

    @TypeConverter
    fun fromJsonToListCharacter(json: String?): List<Characters>? = Gson().fromJson(json,
        object : TypeToken<List<Characters>>() {}.type
    )

    @TypeConverter
    fun fromListStringToJson(stringList: List<String>?): String? = Gson().toJson(stringList)

    @TypeConverter
    fun fromJsonToListString(json: String?): List<String>? = Gson().fromJson(json,
        object : TypeToken<List<String>>() {}.type
    )


    @TypeConverter
    fun fromReviewerToJson(reviewer: Reviewer): String = Gson().toJson(reviewer)

    @TypeConverter
    fun fromJsonToReviewer(json: String): Reviewer = Gson().fromJson(json,
        object : TypeToken<Reviewer>() {}.type
    )

    @TypeConverter
    fun fromScoreToJson(score: Score): String = Gson().toJson(score)

    @TypeConverter
    fun fromJsonToScore(json: String): Score =  Gson().fromJson(json,
        object : TypeToken<Score>() {}.type
    )

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}