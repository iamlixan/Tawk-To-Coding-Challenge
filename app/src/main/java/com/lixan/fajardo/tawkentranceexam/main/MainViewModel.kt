package com.lixan.fajardo.tawkentranceexam.main

import android.os.Bundle
import com.lixan.fajardo.tawkentranceexam.di.base.BaseViewModel
import com.lixan.fajardo.tawkentranceexam.utils.ResourceManager
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val resourceManager: ResourceManager
): BaseViewModel() {

    override fun isFirstTimeUICreated(bundle: Bundle?) = Unit

    private val _state by lazy {
        PublishSubject.create<MainState>()
    }

    val state: Observable<MainState> = _state

}