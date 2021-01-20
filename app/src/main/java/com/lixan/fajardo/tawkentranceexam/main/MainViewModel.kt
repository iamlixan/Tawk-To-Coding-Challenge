package com.lixan.fajardo.tawkentranceexam.main

import android.os.Bundle
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.repository.source.GitUserRepository
import com.lixan.fajardo.tawkentranceexam.di.base.BaseViewModel
import com.lixan.fajardo.tawkentranceexam.utils.KEY_GIT_USER_DATA
import com.lixan.fajardo.tawkentranceexam.utils.ResourceManager
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor (
    private val repository: GitUserRepository,
    private val resourceManager: ResourceManager
): BaseViewModel() {

    override fun isFirstTimeUICreated(bundle: Bundle?) = Unit

    private val _state by lazy {
        PublishSubject.create<MainState>()
    }

    val state: Observable<MainState> = _state

    fun getGitUsers(page: Int = 0) {
        repository.getUsers(page)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribeBy(
                onSuccess = {
                    if (it.isSuccess) {
                        val map = it.result()
                        val gitUsersList = map[KEY_GIT_USER_DATA] as List<GitUser>

                        if (gitUsersList.isEmpty()) {
                            _state.onNext(
                                MainState.Empty
                            )
                        } else {
                            _state.onNext(
                                MainState.Success(gitUsersList)
                            )
                        }
                    } else {
                        it.error().cause?.let { it1 ->
                            MainState.Error(it1)
                        }?.let { it2 ->
                            _state.onNext(
                                it2
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

}