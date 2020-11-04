package com.dargoz.data.utils

import androidx.room.TypeConverter
import com.dargoz.domain.models.Characters
import com.dargoz.domain.models.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverters {

    @TypeConverter
    fun fromListGenreToJson(genres: List<Genre>): String = Gson().toJson(genres)

    @TypeConverter
    fun fromJsonToListGenre(json: String): List<Genre> = Gson().fromJson(json,
        object : TypeToken<List<Genre>>() {}.type)

    @TypeConverter
    fun fromListCharacterToJson(characters: List<Characters>?): String? = Gson().toJson(characters)

    @TypeConverter
    fun fromJsonToListCharacter(json: String?): List<Characters>? = Gson().fromJson(json,
        object : TypeToken<List<Characters>>() {}.type)

    @TypeConverter
    fun fromListStringToJson(stringList: List<String>?): String? = Gson().toJson(stringList)

    @TypeConverter
    fun fromJsonToListString(json: String?): List<String>? = Gson().fromJson(json,
        object : TypeToken<List<String>>() {}.type)


}