package com.lixan.fajardo.tawkentranceexam.di.modules

import com.lixan.fajardo.tawkentranceexam.di.scope.ActivityScope
import com.lixan.fajardo.tawkentranceexam.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}