package com.lixan.fajardo.tawkentranceexam.di

import com.lixan.fajardo.tawkentranceexam.di.base.BaseSchedulerProvider
import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider private constructor(): BaseSchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun <T> applySchedulers(): SingleTransformer<T, T> {
        TODO("Not yet implemented")
    }

    override fun <T> applyMaybeSchedulers(): MaybeTransformer<T, T> {
        TODO("Not yet implemented")
    }

    override fun applyCompletableSchedulers(): CompletableTransformer {
        TODO("Not yet implemented")
    }

    companion object {
        private var INSTANCE: SchedulerProvider? = null

        fun getInstance(): SchedulerProvider {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SchedulerProvider().also { INSTANCE = it }
            }
        }
    }

}