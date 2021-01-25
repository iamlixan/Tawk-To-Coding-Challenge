package com.lixan.fajardo.tawkentranceexam.main.profile

import android.os.Bundle
import android.view.View
import androidx.databinding.Bindable
import com.lixan.fajardo.tawkentranceexam.R
import com.lixan.fajardo.tawkentranceexam.data.repository.source.GitUserRepository
import com.lixan.fajardo.tawkentranceexam.di.base.BaseViewModel
import com.lixan.fajardo.tawkentranceexam.utils.ResourceManager
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class GitUserProfileViewModel @Inject constructor(
    private val repository: GitUserRepository,
    private val resourceManager: ResourceManager
): BaseViewModel() {

    override fun isFirstTimeUICreated(bundle: Bundle?) = Unit

    private val _state by lazy {
        PublishSubject.create<GitUserProfileState>()
    }

    val state: Observable<GitUserProfileState> = _state

    fun getUserProfile(username: String) {
        repository.getUserProfileFromAPI(username)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribeBy(
                onSuccess = {
                    if (it.isSuccess) {
                        _state.onNext(
                            GitUserProfileState.Success(it.result())
                        )
                    } else {
                        _state.onNext(
                            GitUserProfileState.Error(it.error().errorMessage)
                        )
                    }
                },
                onError = {
                    Timber.e(it)
                    _state.onNext(
                        GitUserProfileState.Error(
                            resourceManager.getString(R.string.error_general_error
                            )
                        )
                    )
                }
            )
            .addTo(disposables)
    }
}