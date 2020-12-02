package com.dargoz.capstone.di

import com.dargoz.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideSeasonAnimeUseCase(seasonInteractor: AnimeInteractor): AnimeUseCase

    @Binds
    abstract fun provideAnimeDetailUseCase(animeDetailInteractor: AnimeDetailInteractor): AnimeDetailUseCase

    @Binds
    abstract fun provideCharactersAndStaffUseCase(
        animeCharactersAndStaffInteractor: AnimeCharactersAndStaffInteractor): AnimeCharactersAndStaffUseCase

    @Binds
    abstract fun provideAnimeReviewsUseCase(animeReviewsInteractor: AnimeReviewsInteractor): AnimeReviewsUseCase

    @Binds
    abstract fun provideUpdateAnimeFavoriteUseCase(updateAnimeFavoriteInteractor: UpdateAnimeFavoriteInteractor): UpdateAnimeFavoriteUseCase

    @Binds
    abstract fun provideFavoriteUseCase(favoriteInteractor: FavoriteInteractor): FavoriteUseCase

    @Binds
    abstract fun provideMangaUseCase(mangaInteractor: MangaInteractor): MangaUseCase

    @Binds
    abstract fun provideSearchUseCase(searchInteractor: SearchInteractor): SearchUseCase
}