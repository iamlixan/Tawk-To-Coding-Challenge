package com.lixan.fajardo.tawkentranceexam.di.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    open val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    @Inject
    lateinit var schedulers: BaseSchedulerProvider

    private var isFirstTimeUICreated: Boolean = true

    @CallSuper
    open fun onCreate(bundle: Bundle?) {
        if (isFirstTimeUICreated) {
            isFirstTimeUICreated(bundle)
            isFirstTimeUICreated = false
        }
    }

    abstract fun isFirstTimeUICreated(bundle: Bundle?)

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}