package com.lixan.fajardo.tawkentranceexam.main

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser

sealed class MainState {

    object RemoteEmpty : MainState()

    object LocalEmpty : MainState()

    object ShowProgressLoading: MainState()

    object HideProgressLoading: MainState()

    data class SetData(val gitUsers: List<GitUser>): MainState()

    data class AddData(val gitUsers: List<GitUser>): MainState()

    data class NoInternetError(val message: String): MainState()

    data class Error(val message: Throwable) : MainState()

}