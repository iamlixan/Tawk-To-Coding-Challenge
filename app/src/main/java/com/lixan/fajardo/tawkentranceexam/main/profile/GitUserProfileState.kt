package com.lixan.fajardo.tawkentranceexam.main.profile

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser

sealed class GitUserProfileState {

    data class Success(val gitUserProfile: GitUser): GitUserProfileState()

    data class Error(val errorMessage: String): GitUserProfileState()
}