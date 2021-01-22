package com.lixan.fajardo.tawkentranceexam.main

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser

sealed class MainState {

    object Empty : MainState()

    object ShowProgressLoading: MainState()

    object HideProgressLoading: MainState()

    data class SetData(val gitUsers: List<GitUser>) : MainState()

    data class Error(val message: Throwable) : MainState()

}