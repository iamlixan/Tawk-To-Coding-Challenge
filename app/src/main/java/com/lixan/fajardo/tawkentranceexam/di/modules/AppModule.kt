package com.lixan.fajardo.tawkentranceexam.di.modules

import android.app.Application
import android.content.Context
import com.lixan.fajardo.tawkentranceexam.di.scope.ApplicationContext
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @ApplicationContext
    @Singleton
    @Binds
    abstract fun provideApplicationContext(application: Application): Context
}