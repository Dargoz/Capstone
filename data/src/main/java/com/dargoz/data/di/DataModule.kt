package com.dargoz.data.di

import com.dargoz.data.source.DataRepositoryImpl
import com.dargoz.domain.repositories.IDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideRepository(dataRepositoryImpl: DataRepositoryImpl) : IDataRepository
}