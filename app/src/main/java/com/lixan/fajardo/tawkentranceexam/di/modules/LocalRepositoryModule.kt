package com.lixan.fajardo.tawkentranceexam.di.modules

import com.lixan.fajardo.tawkentranceexam.local.database.AppDatabase
import com.lixan.fajardo.tawkentranceexam.local.implementation.GitUserLocalRepositoryImpl
import com.lixan.fajardo.tawkentranceexam.local.source.GitUserLocalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LocalRepositoryModule {

    @Provides
    @Singleton
    fun providesGitUserLocalRepository(database : AppDatabase): GitUserLocalRepository =
        GitUserLocalRepositoryImpl(database)
}