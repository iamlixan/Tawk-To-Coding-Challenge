package com.lixan.fajardo.tawkentranceexam.main

import android.os.Bundle
import com.lixan.fajardo.tawkentranceexam.data.repository.source.GitUserRepository
import com.lixan.fajardo.tawkentranceexam.di.base.BaseViewModel
import com.lixan.fajardo.tawkentranceexam.utils.ResourceManager
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainViewModel @Inject constructor (
    private val repository: GitUserRepository,
    private val resourceManager: ResourceManager
): BaseViewModel() {

    override fun isFirstTimeUICreated(bundle: Bundle?) = getGitUsers()

    private val _state by lazy {
        PublishSubject.create<MainState>()
    }

    val state: Observable<MainState> = _state

    fun getGitUsers(page: Int = 0) {
        repository.getUsersFromAPI(page)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe {
                _state.onNext(
                    MainState.ShowProgressLoading
                )
            }
            .doOnSuccess {
                _state.onNext(
                    MainState.HideProgressLoading
                )
            }
            .doOnError {
                _state.onNext(
                    MainState.HideProgressLoading
                )
            }
            .subscribeBy(
                onSuccess = {
                    if (it.isEmpty()) {
                        _state.onNext(
                            MainState.Empty
                        )
                    } else {
                        _state.onNext(
                            MainState.SetData(it)
                        )
                    }
                },
                onError = {
                    _state.onNext(
                        MainState.Error(it)
                    )
                }
            )
            .addTo(disposables)
    }

    fun getLocalGitUsers() {
        repository.getLocalGitUsers()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribeBy(
                onSuccess = {
                    if (it.isEmpty()) {
                        _state.onNext(
                            MainState.Empty
                        )
                    } else {
                        _state.onNext(
                            MainState.SetData(it)
                        )
                    }
                },
                onError = {
                    _state.onNext(
                        MainState.Error(it)
                    )
                }
            )
            .addTo(disposables)
    }

}