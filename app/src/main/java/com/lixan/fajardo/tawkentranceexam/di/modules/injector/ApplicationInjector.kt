package com.lixan.fajardo.tawkentranceexam.di.modules.injector

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.lixan.fajardo.tawkentranceexam.TawkEntranceExam
import com.lixan.fajardo.tawkentranceexam.di.component.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

object ApplicationInjector {

    fun init(app: TawkEntranceExam) {
        DaggerAppComponent.builder().application(app)
            .build().inject(app)
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }

            override fun onActivityResumed(activity: Activity) {

            }

        })
    }

    private fun handleActivity(activity: Activity) {

        if (activity is HasSupportFragmentInjector || activity is HasActivityInjector) {
            AndroidInjection.inject(activity)
        }

        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(
                            fm: FragmentManager,
                            f: Fragment,
                            savedInstanceState: Bundle?
                        ) {
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    },true
                )
        }
    }
}