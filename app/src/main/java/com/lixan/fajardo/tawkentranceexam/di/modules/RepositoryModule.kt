package com.lixan.fajardo.tawkentranceexam.di.modules

import com.lixan.fajardo.tawkentranceexam.data.repository.implementation.GitUserRepositoryImpl
import com.lixan.fajardo.tawkentranceexam.data.repository.source.GitUserRepository
import com.lixan.fajardo.tawkentranceexam.local.source.GitUserLocalRepository
import com.lixan.fajardo.tawkentranceexam.network.remoterepository.source.GitUserRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGitUserRepository(
        remote: GitUserRemoteRepository,
        local: GitUserLocalRepository
    ): GitUserRepository = GitUserRepositoryImpl(remote, local)

}