package com.dargoz.capstone.di

import com.dargoz.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideSeasonAnimeUseCase(seasonInteractor: CurrentSeasonInteractor): CurrentSeasonUseCase

    @Binds
    abstract fun provideAnimeDetailUseCase(animeDetailInteractor: AnimeDetailInteractor): AnimeDetailUseCase

    @Binds
    abstract fun provideCharactersAndStaffUseCase(
        animeCharactersAndStaffInteractor: AnimeCharactersAndStaffInteractor): AnimeCharactersAndStaffUseCase

    @Binds
    abstract fun provideAnimeReviewsUseCase(animeReviewsInteractor: AnimeReviewsInteractor): AnimeReviewsUseCase
}