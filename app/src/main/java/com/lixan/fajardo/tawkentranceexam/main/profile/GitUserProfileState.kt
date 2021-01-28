package com.lixan.fajardo.tawkentranceexam.main.profile

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.models.GitUserNote
import androidx.core.util.Pair

sealed class GitUserProfileState {

    data class Success(val gitUserProfile: Pair<GitUser, GitUserNote>): GitUserProfileState()

    data class Error(val errorMessage: String): GitUserProfileState()

    object SaveNoteSuccess: GitUserProfileState()

    object ShowLoadingLayout: GitUserProfileState()

    object HideLoadingLayout: GitUserProfileState()
}