package com.lixan.fajardo.tawkentranceexam.di.modules

import com.lixan.fajardo.tawkentranceexam.di.SchedulerProvider
import com.lixan.fajardo.tawkentranceexam.di.base.BaseSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulerModule {

    @Provides
    @Singleton
    fun providesSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider.getInstance()
}