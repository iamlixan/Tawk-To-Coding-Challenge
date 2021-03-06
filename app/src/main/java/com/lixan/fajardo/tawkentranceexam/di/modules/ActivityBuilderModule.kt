package com.lixan.fajardo.tawkentranceexam.di.modules

import com.lixan.fajardo.tawkentranceexam.di.scope.ActivityScope
import com.lixan.fajardo.tawkentranceexam.main.MainActivity
import com.lixan.fajardo.tawkentranceexam.main.profile.GitUserProfileActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeGitUserProfileActivity(): GitUserProfileActivity

}