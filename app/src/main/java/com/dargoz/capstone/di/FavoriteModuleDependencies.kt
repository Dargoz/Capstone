package com.dargoz.capstone.di

import android.app.Application
import com.dargoz.domain.usecases.FavoriteUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface FavoriteModuleDependencies {

    fun exposeApplication(): Application

    fun exposeFavoriteUseCase(): FavoriteUseCase
}