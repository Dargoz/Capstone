package com.dargoz.capstone.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UseCaseSingletonQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UseCaseFavoriteQualifier