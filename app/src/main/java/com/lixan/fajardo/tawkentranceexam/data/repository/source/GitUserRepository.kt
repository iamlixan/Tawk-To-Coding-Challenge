package com.lixan.fajardo.tawkentranceexam.data.repository.source

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.network.response.RequestResult
import io.reactivex.Single

interface GitUserRepository {

    fun getUsersFromAPI(page: Int = 0): Single<RequestResult<List<GitUser>>>

    fun saveGitUser(gitUser: GitUser): Single<GitUser>

    fun getLocalGitUsers(): Single<List<GitUser>>

    fun saveGitUserList(gitUserList: List<GitUser>): Single<List<GitUser>>

    fun getUserProfileFromAPI(username: String): Single<RequestResult<GitUser>>

    fun getLocalGitUserProfile(username: String): Single<GitUser>
}