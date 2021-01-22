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

    /**
     * @Return True when UI hasn't called ViewModel.onCreate yet.
     *
     * When activity/fragment is reloaded view model will check this flag
     * so it doesn't need to reload data.
     */
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