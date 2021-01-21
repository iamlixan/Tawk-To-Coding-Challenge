package com.lixan.fajardo.tawkentranceexam.local.source

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import io.reactivex.Single

interface GitUserLocalRepository {

    fun getGitUsers(): Single<List<GitUser>>

    fun saveGitUser(gitUser: GitUser): Single<GitUser>

    fun saveGitUserList(gitUserList: List<GitUser>): Single<List<GitUser>>
}