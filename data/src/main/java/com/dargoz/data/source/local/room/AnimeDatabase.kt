package com.dargoz.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dargoz.data.source.local.entity.AnimeEntity
import com.dargoz.data.utils.DataConverters
import javax.inject.Singleton

@Singleton
@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
}