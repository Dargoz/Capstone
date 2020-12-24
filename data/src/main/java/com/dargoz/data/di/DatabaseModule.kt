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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AnimeDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dargoz".toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "Anime.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }


    @Provides
    fun provideAnimeDao(animeDatabase: AnimeDatabase): AnimeDao = animeDatabase.animeDao()

    @Provides
    fun provideReviewDao(animeDatabase: AnimeDatabase): ReviewDao = animeDatabase.reviewDao()

    @Provides
    fun provideMangaDao(animeDatabase: AnimeDatabase): MangaDao = animeDatabase.mangaDao()
}