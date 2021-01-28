package com.lixan.fajardo.tawkentranceexam.local.source

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.models.GitUserNote
import io.reactivex.Single

interface GitUserLocalRepository {

    fun getGitUsers(): Single<List<GitUser>>

    fun getGitUserProfile(username: String): Single<GitUser>

    fun saveGitUser(gitUser: GitUser): Single<GitUser>

    fun saveGitUserList(gitUserList: List<GitUser>): Single<List<GitUser>>

    fun searchGitUser(username: String): Single<List<GitUser>>

    fun getGitUsersNotes(): Single<List<GitUserNote>>

    fun getGitUserNote(id: Int): Single<GitUserNote>

    fun saveGitUserNote(note: GitUserNote): Single<GitUserNote>
}