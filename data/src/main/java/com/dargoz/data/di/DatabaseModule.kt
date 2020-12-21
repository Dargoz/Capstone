package com.dargoz.data.di

import android.content.Context
import androidx.room.Room
import com.dargoz.data.source.local.room.AnimeDao
import com.dargoz.data.source.local.room.AnimeDatabase
import com.dargoz.data.source.local.room.MangaDao
import com.dargoz.data.source.local.room.ReviewDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AnimeDatabase =
        Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "Anime.db").fallbackToDestructiveMigration().build()

    @Provides
    fun provideAnimeDao(animeDatabase: AnimeDatabase): AnimeDao = animeDatabase.animeDao()

    @Provides
    fun provideReviewDao(animeDatabase: AnimeDatabase): ReviewDao = animeDatabase.reviewDao()

    @Provides
    fun provideMangaDao(animeDatabase: AnimeDatabase): MangaDao = animeDatabase.mangaDao()
}