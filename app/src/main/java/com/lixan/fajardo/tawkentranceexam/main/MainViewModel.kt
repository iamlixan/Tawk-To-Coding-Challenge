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

    override fun isFirstTimeUICreated(bundle: Bundle?) = Unit

    private val _state by lazy {
        PublishSubject.create<MainState>()
    }

    private var page: Int = 0
    private var isLoading = false
    private var lastIDSearched: Int = 0

    val state: Observable<MainState> = _state

    fun getGitUsers(isRefresh: Boolean = true) {

        if (isLoading) {
            return
        }
        //if request is not refreshed, then increment page by 1
        if (isRefresh) {
            page = 0
        } else {
            page += lastIDSearched
        }

        repository.getUsersFromAPI(page)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe {
                isLoading = true
                _state.onNext(
                    MainState.ShowProgressLoading
                )
            }
            .doOnSuccess {
                isLoading = false
                _state.onNext(
                    MainState.HideProgressLoading
                )
            }
            .doOnError {
                isLoading = false
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
                        if (isRefresh) {
                            lastIDSearched = it[it.lastIndex].id
                            _state.onNext(
                                MainState.SetData(it)
                            )
                        } else {
                            _state.onNext(
                                MainState.AddData(it)
                            )
                        }

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