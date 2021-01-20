package com.lixan.fajardo.tawkentranceexam.main

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser

sealed class MainState {

    object Empty : MainState()

    data class Success(val gitUsers: List<GitUser>) : MainState()

    data class Error(val message: Throwable) : MainState()

}