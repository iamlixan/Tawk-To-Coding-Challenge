package com.lixan.fajardo.tawkentranceexam.di.component

import android.app.Application
import com.lixan.fajardo.tawkentranceexam.TawkEntranceExam
import com.lixan.fajardo.tawkentranceexam.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        NetworkModule::class,
        SchedulerModule::class,
        ApiServiceModule::class,
        RemoteRepositoryModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: TawkEntranceExam)
}