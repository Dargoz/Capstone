package com.dargoz.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dargoz.data.source.local.entity.AnimeEntity
import com.dargoz.data.source.local.entity.MangaEntity
import com.dargoz.data.source.local.entity.ReviewEntity
import com.dargoz.data.utils.DataConverters
import javax.inject.Singleton

@Singleton
@Database(
    entities = [AnimeEntity::class, ReviewEntity::class, MangaEntity::class],
    version = 1, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
    abstract fun reviewDao(): ReviewDao
    abstract fun mangaDao(): MangaDao

}